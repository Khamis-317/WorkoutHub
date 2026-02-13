package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface SetRepo extends JpaRepository<Set, UUID> {
    @Query("""
     SELECT s FROM Set s
     JOIN s.exercise e
     JOIN e.workout w
     WHERE w.gymRat.id = :userId
     AND (:since IS NULL OR w.createdAt > :since)
     ORDER BY w.createdAt DESC 
     """)
    List<Set> findSetUserIdAndSince (@Param("userId") UUID userId, @Param ("since")OffsetDateTime since);
}
