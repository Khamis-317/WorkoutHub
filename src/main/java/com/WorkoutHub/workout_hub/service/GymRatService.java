package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.auth.RegisterRequest;
import com.WorkoutHub.workout_hub.dto.GymRatUpdateDto;
import com.WorkoutHub.workout_hub.dto.ProfileDto;
import com.WorkoutHub.workout_hub.dto.UserDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface GymRatService {
    List<UserDto> getAllGymRats();
    void createGymRat(RegisterRequest creationDto);
    UserDto getGymRatById(int id);
    ProfileDto getGymRatProfileById(int id);
    ProfileDto updateGymRatById(int id, GymRatUpdateDto updatedGymRat) throws EntityNotFoundException;
    void deleteGymRatById(int id);
}
