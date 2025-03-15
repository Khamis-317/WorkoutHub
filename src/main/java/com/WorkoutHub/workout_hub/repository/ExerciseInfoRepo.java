package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.ExerciseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExerciseInfoRepo extends JpaRepository<ExerciseInfo, Integer> {
    Optional<ExerciseInfo> findExerciseByName(String name);

    @Query("select ex from ExerciseInfo ex " +
            "join fetch ex.muscleGroup " +
            "where ex.id = :data")
    Optional<ExerciseInfo> findExerciseAndMuscleGroupById(@Param("data") int id);
}