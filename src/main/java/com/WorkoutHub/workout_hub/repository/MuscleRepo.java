package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Muscle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuscleRepo extends JpaRepository<Muscle, Integer> {
    List<Muscle> findAllByOrderByNameAsc();
}
