package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.entity.Workout;
import com.WorkoutHub.workout_hub.enums.Visibility;
import com.WorkoutHub.workout_hub.exception.WorkoutVisibilityException;
import com.WorkoutHub.workout_hub.repository.ExerciseRepo;
import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import com.WorkoutHub.workout_hub.repository.WorkoutRepo;
import com.WorkoutHub.workout_hub.response.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    WorkoutRepo workoutRepo;
    ExerciseRepo exerciseRepo;
    GymRatRepo gymRatRepo;

    WorkoutServiceImpl(WorkoutRepo workoutRepo, GymRatRepo gymRatRepo, ExerciseRepo exerciseRepo) {
        this.workoutRepo = workoutRepo;
        this.gymRatRepo = gymRatRepo;
        this.exerciseRepo = exerciseRepo;
    }

    @Override
    public PageResponse<WorkoutDto> getAllWorkouts(int userId, int pageNumber, int pageSize) {
        validateUserExistence(userId);

        Page<Workout> page = workoutRepo.findByGymRatId(userId, PageRequest.of(pageNumber, pageSize));

        Page<WorkoutDto> workouts = page.map(
                workout -> WorkoutDto.createDto(
                        workout,
                        exerciseRepo.findByWorkoutId(workout.getId(), PageRequest.of(0, 3)),
                        false
                )
        );
        return new PageResponse<>(workouts);
    }

    @Override
    public WorkoutDto getWorkoutById(/*do we actually need that??*/int userId, int workoutId) {
        validateUserExistence(userId);

        Workout workout = workoutRepo.findById(workoutId).orElseThrow(() -> new EntityNotFoundException("Entity Not Found: Workout with id: " + workoutId + " is not found."));

       hasViewPermissionForWorkout(userId, workout);

       return WorkoutDto.createDto(workout, workout.getExercises(), true);

    }

    @Override
    public WorkoutDto createWorkout(Workout workout, int userId) {
        return null;
    }



    @Override
    public WorkoutDto updateWorkout(Workout workout) {
        return null;
    }

    @Override
    public void deleteWorkoutById(int id) {

    }

    private void hasViewPermissionForWorkout(int userId, Workout workout) {
        if ((workout.getWorkoutpost().getVisibility() == Visibility.Private) && workout.getGymRat().getId() != userId ){
            throw new WorkoutVisibilityException("Workout Not Visible: User is not owner or friend of the workout owner and it is not public.");
        }
    }

    private void  validateUserExistence(int userId){
        if (!gymRatRepo.existsById(userId)){
            throw new EntityNotFoundException("Entity Not Found: GymRat with id: " + userId + " is not found.");
        }
    }
}
