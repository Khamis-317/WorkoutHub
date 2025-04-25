package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.dto.WorkoutSimpleDto;
import com.WorkoutHub.workout_hub.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface WorkoutRepo extends JpaRepository<Workout, Integer> {

    @Query("SELECT w FROM Workout w JOIN FETCH w.workoutpost WHERE w.gymRat.id = :userId ORDER BY w.createdAt DESC")
    List<Workout> findByGymRatId(int userId, Pageable pageable);
}
