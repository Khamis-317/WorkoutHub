package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MuscleRepo extends JpaRepository<Muscle, Integer> {
    Optional<Muscle> findMuscleByName(String name);

    @Query("select m from Muscle m " +
            "join fetch m.activationExercises " +
            "where m.id = :data")
    Optional<Exercise> findMuscleAndExercisesById(@Param("data") int id);
}
