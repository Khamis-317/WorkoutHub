package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Exercise;

public interface CustomExerciseRepo {
    void merge(Exercise exercise);
}
