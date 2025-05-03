package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.dto.WorkoutSimpleDto;
import com.WorkoutHub.workout_hub.entity.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface WorkoutRepo extends JpaRepository<Workout, Integer> {


    Page<Workout> findByGymRatId(int userId, Pageable pageable);
}
