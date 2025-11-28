package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.WorkoutCreationDto;
import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.ExerciseInfo;
import com.WorkoutHub.workout_hub.entity.Workout;
import com.WorkoutHub.workout_hub.enums.Visibility;
import com.WorkoutHub.workout_hub.exception.WorkoutVisibilityException;
import com.WorkoutHub.workout_hub.mapper.DtoEntityMapper;
import com.WorkoutHub.workout_hub.repository.ExerciseInfoRepo;
import com.WorkoutHub.workout_hub.repository.ExerciseRepo;
import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import com.WorkoutHub.workout_hub.repository.WorkoutRepo;
import com.WorkoutHub.workout_hub.response.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    WorkoutRepo workoutRepo;
    ExerciseRepo exerciseRepo;
    GymRatRepo gymRatRepo;
    ExerciseInfoRepo exerciseInfoRepo;

    WorkoutService(WorkoutRepo workoutRepo, GymRatRepo gymRatRepo, ExerciseRepo exerciseRepo, ExerciseInfoRepo exerciseInfoRepo) {
        this.workoutRepo = workoutRepo;
        this.gymRatRepo = gymRatRepo;
        this.exerciseRepo = exerciseRepo;
        this.exerciseInfoRepo = exerciseInfoRepo;
    }


    public PageResponse<WorkoutDto> getAllWorkouts(UUID userId, int pageNumber, int pageSize) {
        validateUserExistence(userId);

        Page<Workout> page = workoutRepo.findByGymRatId(userId, PageRequest.of(pageNumber, pageSize));

        Page<WorkoutDto> workouts = page.map(
                workout -> DtoEntityMapper.createWorkoutDto(
                        workout,
                        exerciseRepo.findByWorkoutId(workout.getId(), PageRequest.of(0, 3)),
                        false
                )
        );
        return new PageResponse<>(workouts);
    }


    public WorkoutDto getWorkoutById(/*do we actually need that??*/UUID userId, int workoutId) {
        validateUserExistence(userId);

        Workout workout = workoutRepo.findById(workoutId).orElseThrow(() -> new EntityNotFoundException("Entity Not Found: Workout with id: " + workoutId + " is not found."));

       hasViewPermissionForWorkout(userId, workout);

       return DtoEntityMapper.createWorkoutDto(workout, workout.getExercises(), true);

    }

   //Temp
    public UUID createWorkout(WorkoutCreationDto workout, UUID userId) {
        validateUserExistence(userId);
        List<Exercise> exercises = workout.getExercises().stream()
                .map(exerciseDto -> {
                    ExerciseInfo info = exerciseInfoRepo.findById(exerciseDto.getExerciseInfoId()).
                            orElseThrow(() -> new EntityNotFoundException("Entity Not Found: ExerciseInfo with id: " + exerciseDto.getExerciseInfoId() + " is not found."));
                    return DtoEntityMapper.createExerciseEntity(exerciseDto, info);
                })
                .toList();
        Workout createdWorkout = DtoEntityMapper.createWorkoutEntity(workout, exercises, gymRatRepo.findById(userId).get());
        workoutRepo.save(createdWorkout);
        return createdWorkout.getId();
    }




    public WorkoutDto updateWorkout(Workout workout) {
        return null;
    }


    public void deleteWorkoutById(int id) {

    }

    private void hasViewPermissionForWorkout(UUID userId, Workout workout) {
        if ((workout.getWorkoutpost().getVisibility() == Visibility.PRIVATE) && workout.getGymRat().getId() != userId ){
            throw new WorkoutVisibilityException("Workout Not Visible: User is not owner or friend of the workout owner and it is not public.");
        }
    }

    private void  validateUserExistence(UUID userId){
        if (!gymRatRepo.existsById(userId)){
            throw new EntityNotFoundException("Entity Not Found: GymRat with id: " + userId + " is not found.");
        }
    }
}
