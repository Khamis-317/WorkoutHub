package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.GymRatRequestDto;
import com.WorkoutHub.workout_hub.dto.UserDto;
import com.WorkoutHub.workout_hub.response.GenericResponse;
import com.WorkoutHub.workout_hub.service.GymRatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Expose
    @GetMapping("/gymrats")
    public ResponseEntity<?> getAllGymRats() {
        List<UserDto> gymrats = gymRatService.getAllGymRats();
        GenericResponse<?> body = GenericResponse.success(gymrats, "List of all the current gymrats.");
        return new ResponseEntity<>(body ,HttpStatus.OK);
    }

    @PostMapping("/gymrats")
    public ResponseEntity<?> createGymRat(@Valid @RequestBody GymRatRequestDto gymrat) {
        gymRatService.createGymRat(gymrat);
        GenericResponse<?> body = GenericResponse.success("A new gymrat is created successfully.");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @GetMapping("/gymrats/{id}")
    public ResponseEntity<?> getGymRat(@PathVariable int id) {
        GenericResponse<?> body;
        UserDto gymrat = gymRatService.getGymRatById(id);
        body = GenericResponse.success(gymrat, "Gymrat of id: " + id + ".");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

//    @PutMapping("/gymrats/{id}")
//    public GenericResponse<?> updateGymRat(@PathVariable int id, @RequestBody GymRat updatedGymrat) {
//        gymRatService.updateGymRatById(id, updatedGymrat);
//        GenericResponse<?> response = GenericResponse.builder()
//                .message("Updated gymrat of id: " + id + ".")
//                .success(true)
//                .build();
//        return response;
//    }
//
//    @DeleteMapping("/gymrats/{id}")
//    public GenericResponse<?> deleteGymRat(@PathVariable int id) {
//        gymRatService.deleteGymRatById(id);
//        GenericResponse<?> response = GenericResponse.builder()
//                .success(true)
//                .message("Gymrat of id: " + id +  " is deleted successfully.")
//                .build();
//        return response;
//    }
}
