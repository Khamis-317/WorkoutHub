package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.response.ExerciseLibraryResponse;
import com.WorkoutHub.workout_hub.response.GenericResponse;
import com.WorkoutHub.workout_hub.service.WorkoutTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WorkoutTrackingController {

    private final WorkoutTrackingService workoutTrackingService;
    @GetMapping("exercises")
    public ResponseEntity<GenericResponse<ExerciseLibraryResponse>> getExerciseLibrary(){
        var exerciseLibrary = workoutTrackingService.getExerciseLibrary();
        return ResponseEntity.ok(GenericResponse.success(exerciseLibrary));

    }
}
