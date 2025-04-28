package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.GymRatRequestDto;
import com.WorkoutHub.workout_hub.dto.UserDto;
import com.WorkoutHub.workout_hub.entity.GymRat;

import java.util.List;

public interface GymRatService {
    List<UserDto> getAllGymRats();
    void createGymRat(GymRatRequestDto creationDto);
    UserDto getGymRatById(int id);
    void updateGymRatById(int id, GymRat updatedGymRat);
    void deleteGymRatById(int id);
}
