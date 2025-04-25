package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.dto.WorkoutSimpleDto;
import com.WorkoutHub.workout_hub.entity.Workout;

import java.util.List;

public interface WorkoutService {

    List<WorkoutSimpleDto> getAllWorkouts(int userId);

    WorkoutDto createWorkout(Workout workout, int userId);

    WorkoutDto getWorkoutById(int userId);

    WorkoutDto updateWorkout(Workout workout);

    void deleteWorkoutById(int id);
}
