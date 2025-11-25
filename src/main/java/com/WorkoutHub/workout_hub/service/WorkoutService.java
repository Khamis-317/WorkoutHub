package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.WorkoutCreationDto;
import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.entity.Workout;
import com.WorkoutHub.workout_hub.response.PageResponse;

public interface WorkoutService {

    PageResponse<WorkoutDto> getAllWorkouts(int userId, int pageNumber, int pageSize);

    WorkoutDto getWorkoutById(int userId, int workoutId);

    int createWorkout(WorkoutCreationDto workout, int userId);


    WorkoutDto updateWorkout(Workout workout);

    void deleteWorkoutById(int id);
}
