package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.GymRat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymRatRepo extends JpaRepository<GymRat, Integer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT gr FROM GymRat gr JOIN FETCH gr.profile WHERE gr.id = :id")
    Optional<GymRat> findGymRatWithProfileById(int id);

    @Query("SELECT gr FROM GymRat gr JOIN FETCH gr WHERE gr.username= :username")
    Optional<GymRat> findGymRatByUsername(String username);
}