package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.WorkoutRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutRoutineRepo extends JpaRepository<WorkoutRoutine, Integer> {
    @Query("""
        select w from WorkoutRoutine w
        where w.gymRat.id = :data
        """
    )
    List<WorkoutRoutine> findUserRoutinesById(@Param("data") int id);
}
