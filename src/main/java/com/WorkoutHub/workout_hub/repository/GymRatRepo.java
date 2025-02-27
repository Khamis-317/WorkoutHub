package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface GymRatRepo extends JpaRepository<GymRat, Integer> {
    @Query("""
            select u from GymRat u
            left join fetch u.workouts w
            where u.id = :data and Type(w) = WorkoutPost
    """
    )
    Optional<GymRat> fetchUserPostsById(@Param("data") int id);

    @Query("""
            select u from GymRat u
            left join fetch u.workouts w
            where u.id = :data and Type(w) = WorkoutRoutine
    """
    )
    Optional<GymRat> fetchUserRoutinesById(@Param("data") int id);
}