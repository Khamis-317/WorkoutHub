package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.GymRat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GymRatRepo extends JpaRepository<GymRat, UUID> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT gr FROM GymRat gr JOIN FETCH gr.profile WHERE gr.id = :id")
    Optional<GymRat> findGymRatWithProfileById(UUID id);


    Optional<GymRat> findGymRatByUsername(String username);
    Optional<GymRat> findGymRatByUsernameOrEmail(String username, String email);
}