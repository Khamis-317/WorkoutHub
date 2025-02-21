package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.GymRat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GymRatRepo extends JpaRepository<GymRat, Integer> {

}
