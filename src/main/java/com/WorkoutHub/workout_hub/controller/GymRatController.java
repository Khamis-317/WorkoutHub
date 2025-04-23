package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.GymRatCreationDto;
import com.WorkoutHub.workout_hub.dto.UserDto;
import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.exception.GymRatNotFoundException;
import com.WorkoutHub.workout_hub.response.GenericResponse;
import com.WorkoutHub.workout_hub.service.GymRatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GymRatController {
    private GymRatService gymRatService;

    @Autowired
    public GymRatController(GymRatService grService) {
        this.gymRatService = grService;
    }

    @GetMapping("/gymrats")
    public GenericResponse<?> getAllGymRats() {
        List<UserDto> gymrats = gymRatService.getAllGymRats();
        GenericResponse<?> response = GenericResponse.builder()
                .data(gymrats)
                .success(true)
                .message("List of all the current gymrats")
                .build();
        return response;
    }

    @PostMapping("/gymrats")
    public GenericResponse<?> createGymRat(@RequestBody GymRatCreationDto gymrat) {
        gymRatService.createGymRat(gymrat);
        GenericResponse<?> response = GenericResponse.builder()
                .message("A new gymrat is created successfully")
                .success(true)
                .build();
        return response;
    }

    @GetMapping("/gymrats/{id}")
    public GenericResponse<?> getGymRat(@PathVariable int id) {
        GenericResponse<?> response;
        try {
            UserDto gymrat = gymRatService.getGymRatById(id);
            response = GenericResponse.builder()
                    .data(gymrat)
                    .success(true)
                    .message("Gymrat of id: " + id)
                    .build();
        } catch (GymRatNotFoundException e) {
            response = GenericResponse.builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
        return response;
    }

    @PutMapping("/gymrats/{id}")
    public GenericResponse<?> updateGymRat(@PathVariable int id, @RequestBody GymRat updatedGymrat) {
        gymRatService.updateGymRatById(id, updatedGymrat);
        GenericResponse<?> response = GenericResponse.builder()
                .message("Updated gymrat of id: " + id)
                .success(true)
                .build();
        return response;
    }

    @DeleteMapping("/gymrats/{id}")
    public GenericResponse<?> deleteGymRat(@PathVariable int id) {
        gymRatService.deleteGymRatById(id);
        GenericResponse<?> response = GenericResponse.builder()
                .success(true)
                .message("Gymrat of id: " + id +  " is deleted successfully")
                .build();
        return response;
    }
}
