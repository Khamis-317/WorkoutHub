package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Integer>, CustomExerciseRepo {
    Optional<Exercise> findExerciseByName(String name);

    @Query("select ex from Exercise ex " +
            "join fetch ex.muscleGroup " +
            "where ex.id = :data")
    Optional<Exercise> findExerciseAndMuscleGroupById(@Param("data") int id);
}