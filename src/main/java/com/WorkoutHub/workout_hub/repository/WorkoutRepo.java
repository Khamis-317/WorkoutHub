package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WorkoutRepo extends JpaRepository<Workout, UUID> {

    @Query("SELECT w FROM Workout w JOIN FETCH w.exercises WHERE w.id = :id")
    Optional<Workout> findById(int id);

    Page<Workout> findByGymRatId(UUID userId, Pageable pageable);
}
