package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.dto.WorkoutSimpleDto;
import com.WorkoutHub.workout_hub.entity.Workout;
import com.WorkoutHub.workout_hub.response.PageResponse;

import java.util.List;

public interface WorkoutService {

    PageResponse<WorkoutSimpleDto> getAllWorkouts(int userId, int pageNumber, int pageSize);

    WorkoutDto createWorkout(Workout workout, int userId);

    WorkoutDto getWorkoutById(int userId);

    WorkoutDto updateWorkout(Workout workout);

    void deleteWorkoutById(int id);
}
