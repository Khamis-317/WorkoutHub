package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findByName(String name);
}