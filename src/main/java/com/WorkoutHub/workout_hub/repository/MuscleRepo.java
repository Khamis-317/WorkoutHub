package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.ExerciseInfo;
import com.WorkoutHub.workout_hub.entity.Muscle;
import com.WorkoutHub.workout_hub.enums.MuscleImportance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MuscleRepo extends JpaRepository<Muscle, Integer> {

    @Query("select m from Muscle m " +
            "join fetch m.activationExercises " +
            "where m.id = :data")
    Optional<Muscle> findMuscleAndExercisesById(@Param("data") int id);

    Optional<Muscle> findByNameAndImportance(String name, MuscleImportance importance);
}
