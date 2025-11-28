package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Exercise;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExerciseRepo extends JpaRepository<Exercise, UUID> {

    List<Exercise> findByWorkoutId(UUID workoutId, Pageable pageable);
}
