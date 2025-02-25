package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MuscleRepo extends JpaRepository<Muscle, Integer> {
    Optional<Muscle> findMuscleByName(String name);
}
