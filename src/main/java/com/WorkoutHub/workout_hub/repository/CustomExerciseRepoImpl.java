package com.WorkoutHub.workout_hub.repository;

import com.WorkoutHub.workout_hub.entity.Exercise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class CustomExerciseRepoImpl implements CustomExerciseRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void merge(Exercise exercise) {
        entityManager.merge(exercise);
    }
}
