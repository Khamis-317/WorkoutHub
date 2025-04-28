package com.WorkoutHub.workout_hub.validator;

import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final GymRatRepo gymRatRepository;

    @Autowired
    public UniqueEmailValidator(GymRatRepo repo) {
        this.gymRatRepository = repo;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !gymRatRepository.existsByEmail(email);
    }
}
