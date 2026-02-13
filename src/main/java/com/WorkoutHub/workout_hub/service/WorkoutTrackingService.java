package com.WorkoutHub.workout_hub.service;


import com.WorkoutHub.workout_hub.dto.request.WorkoutUploadRequest;
import com.WorkoutHub.workout_hub.dto.response.*;
import com.WorkoutHub.workout_hub.entity.*;
import com.WorkoutHub.workout_hub.entity.Set;
import com.WorkoutHub.workout_hub.mapper.ExerciseInfoMapper;
import com.WorkoutHub.workout_hub.mapper.WorkoutMapper;
import com.WorkoutHub.workout_hub.repository.*;
import com.WorkoutHub.workout_hub.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutTrackingService {
    private final ExerciseInfoRepo exerciseInfoRepo;
    private final ExerciseInfoMapper exerciseInfoMapper;
    private final MuscleRepo muscleRepo;
    private final WorkoutRepo workoutRepo;
    private final WorkoutMapper workoutMapper;
    private final GymRatRepo gymRatRepo;
    private final SetRepo setRepo;
    /**
     * Retrieves the complete exercise library containing all muscles and exercises with their muscle mappings.
     * @return {@link ExerciseLibraryResponse} containing lists of muscles and exercises
     */
    @Transactional(readOnly = true)
    public ExerciseLibraryResponse getExerciseLibrary(){
        var musclesEntity = muscleRepo.findAllByOrderByNameAsc();
        var exercisesEntity = exerciseInfoRepo.findAllWithMuscles();

        var musclesDto = exerciseInfoMapper.toMuscleDtoList(musclesEntity);
        var exercisesDto = exerciseInfoMapper.toExerciseInfoDtoList(exercisesEntity);

        return ExerciseLibraryResponse.builder()
                .muscles(musclesDto)
                .exercises(exercisesDto)
                .build();
    }


    /**
     * Saves a completed workout uploaded from the mobile app.
     * Uses CLIENT-GENERATED UUIDs for idempotency (prevents duplicate uploads).
     * Creates both Workout and WorkoutPost entities (inseparable).
     * Calculates total volume, number of sets, and duration.
     * Returns the saved workout along with exercise history summary for cache update.
     *
     * @param request Workout data from mobile app (includes client-generated UUIDs)
     * @param userId User who performed the workout
     * @return Response containing workout ID, sync timestamp, and new exercise history
     */
    @Transactional
    public WorkoutUploadResponse saveWorkout(WorkoutUploadRequest request, UUID userId) {
        if (workoutRepo.existsById(request.getWorkoutId())) {
            Workout existingWorkout = workoutRepo.findById(request.getWorkoutId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Workout not found with id: " + request.getWorkoutId()));
            return buildWorkoutResponse(existingWorkout);
        }


        GymRat gymRat = gymRatRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));


        long durationMinutes = Duration.between(request.getStartedAt(), request.getCompletedAt()).toMinutes();


        Workout workout = Workout.builder()
                .id(request.getWorkoutId())
                .gymRat(gymRat)
                .totalVolume(0.0)
                .numberOfSets(0)
                .exercises(new ArrayList<>())
                .build();

        double totalVolume = 0.0;
        int totalSets = 0;


        for (var exerciseDto : request.getExercises()) {
            ExerciseInfo exerciseInfo = exerciseInfoRepo.findById(exerciseDto.getExerciseInfoId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Exercise not found with id: " + exerciseDto.getExerciseInfoId()));


            Exercise exercise = Exercise.builder()
                    .id(exerciseDto.getExerciseUuid())
                    .exerciseInfo(exerciseInfo)
                    .orderInWorkout(exerciseDto.getOrderInWorkout())
                    .sets(new ArrayList<>())
                    .build();


            for (var setDto : exerciseDto.getSets()) {
                Set set = workoutMapper.toEntity(setDto);
                set.setId(setDto.getSetUuid());


                exercise.addSet(set);


                totalVolume += set.getWeight() * set.getReps();
                totalSets++;
            }


            workout.addExercise(exercise);
        }


        workout.setTotalVolume(totalVolume);
        workout.setNumberOfSets(totalSets);

        WorkoutPost workoutPost = WorkoutPost.builder()
                .id(request.getWorkoutPostId())
                .title(request.getTitle())
                .caption(request.getCaption())
                .startTime(request.getStartedAt())
                .finishTime(request.getCompletedAt())
                .duration((int) durationMinutes)
                .visibility(request.getVisibility())
                .workout(workout)
                .build();


        workout.setWorkoutpost(workoutPost);
        Workout savedWorkout = workoutRepo.save(workout);

        return buildWorkoutResponse(savedWorkout);
    }


    /**
     * Retrieves exercise history for a user.
     * If 'since' is null, returns full history (for initial load/reinstall).
     * If 'since' is provided, returns only sets performed after that timestamp (delta sync).
     *
     * @param userId User ID
     * @param since Optional timestamp for delta sync (null = full history)
     * @return Exercise history grouped by exercise
     */
    @Transactional(readOnly = true)
    public ExerciseHistoryResponse getUserExerciseHistory(UUID userId, OffsetDateTime since) {
        if (!gymRatRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        List<Set> sets = setRepo.findSetsByUserIdAndSince(userId, since);

        // Group sets by exercise
        Map<Integer, List<Set>> setsByExercise = sets.stream()
                .collect(Collectors.groupingBy(
                        set -> set.getExercise().getExerciseInfo().getId()
                ));


        List<ExerciseHistoryDto> exerciseHistory = setsByExercise.entrySet().stream()
                .map(entry -> {
                    Integer exerciseId = entry.getKey();
                    List<Set> exerciseSets = entry.getValue();

                    String exerciseName = exerciseSets.getFirst().getExercise().getExerciseInfo().getName();

                    // Find most recent performance
                    OffsetDateTime lastPerformed = exerciseSets.stream()
                            .map(Set::getCreatedAt)
                            .max(OffsetDateTime::compareTo)
                            .orElse(null);

                    // Map all sets to history DTOs
                    List<SetHistoryDto> setHistoryDtos = workoutMapper.toHistoryDtoList(exerciseSets);

                    return ExerciseHistoryDto.builder()
                            .exerciseInfoId(exerciseId)
                            .exerciseName(exerciseName)
                            .lastPerformed(lastPerformed)
                            .allTimeSets(setHistoryDtos)
                            .build();
                })
                .sorted(Comparator.comparing(ExerciseHistoryDto::getLastPerformed).reversed())
                .collect(Collectors.toList());

        return ExerciseHistoryResponse.builder()
                .lastSync(OffsetDateTime.now())
                .exerciseHistory(exerciseHistory)
                .build();
    }




    /**
     * Helper method to build workout upload response.
     * Used for both new uploads and idempotent duplicate uploads.
     */
    private WorkoutUploadResponse buildWorkoutResponse(Workout workout) {
        var exerciseHistorySummary = workoutMapper.toExerciseHistorySummaryList(workout.getExercises());

        return WorkoutUploadResponse.builder()
                .workoutId(workout.getId())
                .syncedAt(workout.getCreatedAt())
                .totalVolume(workout.getTotalVolume())
                .numberOfSets(workout.getNumberOfSets())
                .newExerciseHistory(exerciseHistorySummary)
                .build();
    }

}
