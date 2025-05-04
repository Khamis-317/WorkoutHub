package com.WorkoutHub.workout_hub.exception;

import com.WorkoutHub.workout_hub.response.GenericResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    // GymRat Exceptions handlers
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleGymRatNotFoundException(EntityNotFoundException e) {
        GenericResponse<?> error = GenericResponse.error(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    //Workout Visibility Exception ->User Not Owner, Not Friend, Workout Not Public
    @ExceptionHandler(WorkoutVisibilityException.class)
    public ResponseEntity<?> handleWorkoutVisibilityException(WorkoutVisibilityException e) {
        GenericResponse<?> body = GenericResponse.error(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }


    // Invalid request body exceptions handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );
        GenericResponse<?> error = GenericResponse.error(errors, "Invalid Request Content.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Persistence layer constraint violation exceptions handler
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDBException(DataIntegrityViolationException e) {
        GenericResponse<?> error = GenericResponse.error("Data Integrity Violation: due to violating entity constraints.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
