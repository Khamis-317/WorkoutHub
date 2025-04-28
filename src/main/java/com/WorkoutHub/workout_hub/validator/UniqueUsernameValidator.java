package com.WorkoutHub.workout_hub.validator;

import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final GymRatRepo gymRatRepository;

    @Autowired
    public UniqueUsernameValidator(GymRatRepo repo) {
        this.gymRatRepository = repo;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !gymRatRepository.existsByUsername(username);
    }
}
