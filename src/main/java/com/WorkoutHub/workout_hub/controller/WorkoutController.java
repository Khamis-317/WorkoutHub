package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.WorkoutSimpleDto;
import com.WorkoutHub.workout_hub.response.GenericResponse;
import com.WorkoutHub.workout_hub.response.PageResponse;
import com.WorkoutHub.workout_hub.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gymrats/{userId}")
public class WorkoutController {

    WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService){this.workoutService = workoutService;}


    @GetMapping("/workouts")
    public ResponseEntity<?> getAllWorkouts(
            @PathVariable int userId,
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4" , required = false) int pageSize
    ){
      PageResponse<WorkoutSimpleDto> body = workoutService.getAllWorkouts(userId, pageNumber, pageSize);
      return new ResponseEntity<>(body ,HttpStatus.OK);
    }



}
