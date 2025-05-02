package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.GymRatCreationDto;
import com.WorkoutHub.workout_hub.dto.GymRatUpdateDto;
import com.WorkoutHub.workout_hub.dto.ProfileDto;
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

    // Expose an endpoint to list all current gymrats
    @GetMapping("/gymrats")
    public ResponseEntity<?> getAllGymRats() {
        List<UserDto> gymrats = gymRatService.getAllGymRats();
        GenericResponse<?> body = GenericResponse.success(gymrats, "List of all the current gymrats.");
        return new ResponseEntity<>(body ,HttpStatus.OK);
    }

    // Expose an endpoint to create a new gymrat
    @PostMapping("/gymrats")
    public ResponseEntity<?> createGymRat(@Valid @RequestBody GymRatCreationDto gymrat) {
        gymRatService.createGymRat(gymrat);
        GenericResponse<?> body = GenericResponse.success("A new gymrat is created successfully.");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    // Expose an endpoint to get gymrat by id
    @GetMapping("/gymrats/{id}")
    public ResponseEntity<?> getGymRat(@PathVariable int id) {
        UserDto gymrat = gymRatService.getGymRatById(id);
        GenericResponse<?>  body = GenericResponse.success(gymrat, "Gymrat of id: " + id + " returned.");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // Expose an endpoint to get gymrat with profile data by id
    @GetMapping("/gymrats/{id}/profile")
    public ResponseEntity<?> getGymRatProfile(@PathVariable int id) {
        ProfileDto gymrat = gymRatService.getGymRatProfileById(id);
        GenericResponse<?> body = GenericResponse.success(gymrat, "Gymrat of id: " + id + " returned along with gymrat's profile.");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/gymrats/{id}")
    public ResponseEntity<?> updateGymRat(@PathVariable int id, @RequestBody GymRatUpdateDto updateDto) {
        ProfileDto updated = gymRatService.updateGymRatById(id, updateDto);
        GenericResponse<?> body = GenericResponse.success(updated, "Gymrat of id: " + id + " updated successfully.");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

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
