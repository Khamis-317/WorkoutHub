package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.WorkoutPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutPostRepo extends JpaRepository<WorkoutPost, Integer> {
    @Query("""
        select w from WorkoutPost w
        where w.gymRat.id = :data
        """
    )
    List<WorkoutPost> findUserPostsById(@Param("data") int id);


}
