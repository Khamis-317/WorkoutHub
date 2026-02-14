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

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutTrackingService {

    /** Result of saving a workout: response + whether it was already synced (idempotent). */
    public record SaveWorkoutResult(WorkoutUploadResponse response, boolean alreadySynced) {}

    private final ExerciseInfoRepo exerciseInfoRepo;
    private final ExerciseInfoMapper exerciseInfoMapper;
    private final MuscleRepo muscleRepo;
    private final WorkoutRepo workoutRepo;
    private final WorkoutMapper workoutMapper;
    private final GymRatRepo gymRatRepo;
    private final SetRepo setRepo;

    /** Get full exercise library (muscles + exercises). */
    @Transactional(readOnly = true)
    public ExerciseLibraryResponse getExerciseLibrary() {
        List<Muscle> muscles = muscleRepo.findAllByOrderByNameAsc();
        List<ExerciseInfo> exercises = exerciseInfoRepo.findAllWithMuscles();

        return ExerciseLibraryResponse.builder()
                .muscles(exerciseInfoMapper.toMuscleDtoList(muscles))
                .exercises(exerciseInfoMapper.toExerciseInfoDtoList(exercises))
                .build();
    }

    /**
     * Save a workout uploaded from the mobile app.
     * Idempotent: returns 200 if already synced, 201 if newly saved.
     * Creates Workout + WorkoutPost with client-provided UUIDs and timestamps.
     *
     * @param request Workout data with client-generated UUIDs
     * @param userId  User performing the workout
     * @return Result with response and sync status
     */
    @Transactional
    public SaveWorkoutResult saveWorkout(WorkoutUploadRequest request, UUID userId) {
        if (workoutRepo.existsById(request.getWorkoutId())) {
            Workout existingWorkout = workoutRepo.findById(request.getWorkoutId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Workout not found with id: " + request.getWorkoutId()));
            return new SaveWorkoutResult(buildWorkoutResponse(existingWorkout, userId), true);
        }

        GymRat gymRat = gymRatRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        OffsetDateTime performedAt = request.getPerformedAt();

        Workout workout = Workout.builder()
                .id(request.getWorkoutId())
                .gymRat(gymRat)
                .performedAt(performedAt)
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
                    .performedAt(performedAt)
                    .sets(new ArrayList<>())
                    .build();

            for (var setDto : exerciseDto.getSets()) {
                Set set = workoutMapper.toEntity(setDto);
                set.setId(setDto.getSetUuid());
                set.setPerformedAt(performedAt);
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
                .duration(request.getDuration())
                .visibility(request.getVisibility())
                .workout(workout)
                .build();

        workout.setWorkoutpost(workoutPost);
        Workout savedWorkout = workoutRepo.save(workout);

        return new SaveWorkoutResult(buildWorkoutResponse(savedWorkout, userId), false);
    }

    /**
     * Get exercise history for a user.
     * Returns full history if since is null, otherwise delta sync.
     *
     * @param userId User ID
     * @param since  Optional timestamp for delta sync
     * @return Exercise history with history_map and last_workout_layout
     */
    @Transactional(readOnly = true)
    public ExerciseHistoryResponse getUserExerciseHistory(UUID userId, OffsetDateTime since) {
        if (!gymRatRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        List<Set> historyMapSets;
        List<Integer> exerciseInfoIds;

        if (since == null) {
            historyMapSets = setRepo.findHistoryMapByUserId(userId);
            exerciseInfoIds = extractDistinctExerciseInfoIds(historyMapSets);
        } else {
            exerciseInfoIds = setRepo.findExerciseInfoIdsPerformedSince(userId, since);
            if (exerciseInfoIds.isEmpty()) {
                return ExerciseHistoryResponse.builder()
                        .lastSync(OffsetDateTime.now())
                        .exerciseHistory(List.of())
                        .build();
            }
            historyMapSets = setRepo.findHistoryMapByUserIdAndExercises(userId, exerciseInfoIds);
        }

        // Batch-fetch supplementary data
        Map<Integer, List<Set>> historyByExercise = groupByExerciseInfoId(historyMapSets);
        Map<Integer, List<Set>> lastWorkoutByExercise = groupByExerciseInfoId(
                setRepo.findLastWorkoutLayoutByUserIdAndExercise(userId, exerciseInfoIds)
        );
        Map<Integer, Integer> maxPositions = parseMaxPositions(
                setRepo.findMaxPositionByUserIdAndExercises(userId, exerciseInfoIds)
        );

        // Build one DTO per exercise
        List<ExerciseHistoryDto> exerciseHistory = historyByExercise.entrySet().stream()
                .map(entry -> {
                    Integer exerciseId = entry.getKey();
                    List<Set> exerciseSets = entry.getValue();
                    List<Set> layoutSets = lastWorkoutByExercise.getOrDefault(exerciseId, List.of());

                    return buildExerciseHistoryDto(
                            exerciseId,
                            exerciseSets,
                            layoutSets,
                            maxPositions.get(exerciseId)
                    );
                })
                .sorted(Comparator.comparing(ExerciseHistoryDto::getLastPerformed).reversed())
                .toList();

        return ExerciseHistoryResponse.builder()
                .lastSync(OffsetDateTime.now())
                .exerciseHistory(exerciseHistory)
                .build();
    }


    /** Build upload response with updated exercise history for cached exercises. */
    private WorkoutUploadResponse buildWorkoutResponse(Workout workout, UUID userId) {
        List<Integer> exerciseInfoIds = workout.getExercises().stream()
                .map(exercise -> exercise.getExerciseInfo().getId())
                .toList();

        Map<Integer, List<Set>> historyByExercise = groupByExerciseInfoId(
                setRepo.findHistoryMapByUserIdAndExercises(userId, exerciseInfoIds)
        );
        Map<Integer, Integer> maxPositions = parseMaxPositions(
                setRepo.findMaxPositionByUserIdAndExercises(userId, exerciseInfoIds)
        );

        List<ExerciseHistoryDto> newExerciseHistory = workout.getExercises().stream()
                .map(exercise -> {
                    Integer exerciseId = exercise.getExerciseInfo().getId();

                    return buildExerciseHistoryDto(
                            exerciseId,
                            historyByExercise.getOrDefault(exerciseId, List.of()),
                            null,
                            maxPositions.get(exerciseId)
                    );
                })
                .toList();

        return WorkoutUploadResponse.builder()
                .workoutId(workout.getId())
                .syncedAt(OffsetDateTime.now())
                .totalVolume(workout.getTotalVolume())
                .numberOfSets(workout.getNumberOfSets())
                .newExerciseHistory(newExerciseHistory)
                .build();
    }

    /** Build a single ExerciseHistoryDto from sets and optional layout. */
    private ExerciseHistoryDto buildExerciseHistoryDto(Integer exerciseInfoId,
                                                       List<Set> historySets,
                                                       List<Set> layoutSets,
                                                       Integer maxPosition) {
        String exerciseName = historySets.getFirst()
                .getExercise().getExerciseInfo().getName();

        OffsetDateTime lastPerformed = historySets.stream()
                .map(Set::getPerformedAt)
                .max(OffsetDateTime::compareTo)
                .orElse(null);

        List<SetLookupEntryDto> historyMap = workoutMapper.toSetLookupEntryList(historySets);

        List<SetLookupEntryDto> lastWorkoutLayout = (layoutSets != null)
                ? workoutMapper.toSetLookupEntryList(layoutSets)
                : null;

        return ExerciseHistoryDto.builder()
                .exerciseInfoId(exerciseInfoId)
                .exerciseName(exerciseName)
                .lastPerformed(lastPerformed)
                .maxPosition(maxPosition)
                .historyMap(historyMap)
                .lastWorkoutLayout(lastWorkoutLayout)
                .build();
    }

    /** Group sets by their exercise_info_id. */
    private Map<Integer, List<Set>> groupByExerciseInfoId(List<Set> sets) {
        return sets.stream()
                .collect(Collectors.groupingBy(
                        set -> set.getExercise().getExerciseInfo().getId()
                ));
    }

    /** Extract distinct exercise_info_ids from a list of sets. */
    private List<Integer> extractDistinctExerciseInfoIds(List<Set> sets) {
        return sets.stream()
                .map(set -> set.getExercise().getExerciseInfo().getId())
                .distinct()
                .toList();
    }

    /** Parse native query Object[] rows into a Map of exerciseInfoId to maxPosition. */
    private Map<Integer, Integer> parseMaxPositions(List<Object[]> rows) {
        return rows.stream()
                .collect(Collectors.toMap(
                        row -> (Integer) row[0],
                        row -> ((Number) row[1]).intValue()
                ));
    }
}
