package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleRepository extends JpaRepository<Muscle, Integer> {
    
}
