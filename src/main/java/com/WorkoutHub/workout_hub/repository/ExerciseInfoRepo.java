package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.ExerciseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseInfoRepo extends JpaRepository<ExerciseInfo, Integer> {

    @Query("SELECT DISTINCT e FROM ExerciseInfo e " +
            "LEFT JOIN FETCH e.muscleGroup em " +
            "LEFT JOIN FETCH em.muscle m " +
            "ORDER BY e.name ASC, " +
            "em.importance ASC, " +
            "m.name ASC")
     List<ExerciseInfo> findAllWithMuscles();
}