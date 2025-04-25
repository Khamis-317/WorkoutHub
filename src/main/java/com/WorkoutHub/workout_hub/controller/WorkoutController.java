package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.WorkoutSimpleDto;
import com.WorkoutHub.workout_hub.response.GenericResponse;
import com.WorkoutHub.workout_hub.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gymrats/{userId}")
public class WorkoutController {

    WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService){this.workoutService = workoutService;}

    @GetMapping("/workouts")
    public ResponseEntity<?> getAllWorkouts(@PathVariable int userId){
        List<WorkoutSimpleDto> workouts = workoutService.getAllWorkouts(userId);
        return ResponseEntity.ok(GenericResponse.success(workouts));
    }
}
