package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.response.GenericResponse;
import com.WorkoutHub.workout_hub.response.PageResponse;
import com.WorkoutHub.workout_hub.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
      PageResponse<WorkoutDto> body = workoutService.getAllWorkouts(userId, pageNumber, pageSize);
      return new ResponseEntity<>(GenericResponse.success(body) ,HttpStatus.OK);
    }


    @GetMapping("/workouts/{workoutId}")
    public ResponseEntity<?> getWorkoutById(@PathVariable int userId, @PathVariable int workoutId){
        WorkoutDto body = workoutService.getWorkoutById(userId, workoutId);
        return new ResponseEntity<>(GenericResponse.success(body) ,HttpStatus.OK);
    }

}
