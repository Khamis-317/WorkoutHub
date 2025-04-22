package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.GymRatDto;
import com.WorkoutHub.workout_hub.entity.GymRat;

import java.util.List;

public interface GymRatService {
    List<GymRat> getAllGymRats();
    void createGymRat(GymRatDto dto);
    GymRat getGymRatById(int id);
    void updateGymRatById(int id, GymRat updatedGymRat);
    void deleteGymRatById(int id);
}
