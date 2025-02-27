package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
//Could be deleted
public interface WorkoutRepo extends JpaRepository<Workout, Integer> {

}
