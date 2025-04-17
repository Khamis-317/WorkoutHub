package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.entity.GymRat;

import java.util.List;

public interface GymRatService {
    List<GymRat> getAllGymRats();
    void createGymRat(GymRat gymrat);
    GymRat getGymRatById(int id);
    void updateGymRatById(int id, GymRat updatedGymRat);
    void deleteGymRatById(int id);
}
