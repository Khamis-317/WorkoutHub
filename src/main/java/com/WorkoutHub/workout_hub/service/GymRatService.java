package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.entity.GymRat;

import java.util.List;

public interface GymRatService {
    List<GymRat> getAllGymRats();
    void createGymRat(GymRat gymrat);
    GymRat getGymRat(int id);
    void updateGymRat(int id, GymRat updatedGymRat);
    void deleteGymRat(int id);
}
