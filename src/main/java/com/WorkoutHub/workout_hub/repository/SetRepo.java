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

    // For each exercise a user has ever done, find the most recent (reps, weight)
    // for every unique (exercise_info_id, set_number, set_type) combination
    @Query(nativeQuery = true, value = """
    SELECT s.id, s.set_number, s.reps, s.weight, s.set_type, s.performed_at,
           s.exercise_id, s.created_at, s.updated_at
    FROM (
        SELECT DISTINCT ON (we.exercise_info_id, s.set_number, s.set_type)
               s.*
        FROM sets s
        JOIN workout_exercises we ON we.id = s.exercise_id
        JOIN workouts w ON w.id = we.workout_id
        WHERE w.gym_rat_id = :userId
        ORDER BY we.exercise_info_id, s.set_number, s.set_type, s.performed_at DESC
    ) s
    ORDER BY s.exercise_id, s.set_number
""")
    List<Set> findHistoryMapByUserId(@Param("userId") UUID userId);

    // Same as Query 1, but only for a specific list of exercises
    @Query(nativeQuery = true, value = """
        SELECT s.id, s.set_number, s.reps, s.weight, s.set_type, s.performed_at,
           s.exercise_id, s.created_at, s.updated_at
        FROM (
            SELECT DISTINCT ON (we.exercise_info_id, s.set_number, s.set_type)
               s.*
        FROM sets s
        JOIN workout_exercises we ON we.id = s.exercise_id
        JOIN workouts w ON w.id = we.workout_id
        WHERE w.gym_rat_id = :userId AND we.exercise_info_id IN (:exerciseInfoIds)
        ORDER BY we.exercise_info_id, s.set_number, s.set_type, s.performed_at DESC
        ) s
        ORDER BY s.exercise_id, s.set_number
""")
    List<Set> findHistoryMapByUserIdAndExercises(@Param("userId") UUID userId, @Param("exerciseInfoIds") List<Integer> exerciseInfoIds);


    // Find Last Workout's sets for specific Exercises
    @Query(nativeQuery = true, value = """
        SELECT s.id, s.set_number, s.reps, s.weight, s.set_type, s.performed_at,
                      s.exercise_id, s.created_at, s.updated_at
        FROM sets s
        JOIN workout_exercises we ON we.id = s.exercise_id
        JOIN workouts w on w.id = we.workout_id
        WHERE W.gym_rat_id = :userId AND we .exercise_info_id IN (:exerciseInfoIds)
            AND w.performed_at = (
                    SELECT MAX(w2.performed_at)
                    FROM workouts w2
                    JOIN workout_exercises we2 ON we2.workout_id = w2.id
                    WHERE w2.gym_rat_id = :userId AND we2.exercise_info_id = we.exercise_info_id
                )
            ORDER BY we.exercise_info_id, s.set_number
    """)
    List<Set> findLastWorkoutLayoutByUserIdAndExercise(@Param("userId") UUID userId, @Param("exerciseInfoIds") List<Integer> exerciseInfoIds);


    // Find Exercise Info that performed since a given date
    @Query(nativeQuery = true, value = """
    SELECT DISTINCT we.exercise_info_id
    FROM workout_exercises we
    JOIN workouts w ON w.id = we.workout_id
    WHERE w.gym_rat_id = :userId AND  w.performed_at > :since
    """)
    List<Integer> findExerciseInfoIdsPerformedSince(
            @Param("userId") UUID userId,
            @Param("since") OffsetDateTime since
    );


    // Max Set Number Per Exercise
    @Query(nativeQuery = true, value = """
    SELECT we.exercise_info_id, MAX(s.set_number) as max_position
    FROM sets s
    JOIN workout_exercises we ON we.id = s.exercise_id
    JOIN workouts w ON w.id = we.workout_id
    WHERE w.gym_rat_id = :userId AND we.exercise_info_id IN (:exerciseInfoIds)
    GROUP BY we.exercise_info_id
    """)
    List<Object[]> findMaxPositionByUserIdAndExercises(
            @Param("userId") UUID userId,
            @Param("exerciseInfoIds") List<Integer> exerciseInfoIds
    );
}
