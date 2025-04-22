package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.GymRatDto;
import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.entity.GymRatProfile;
import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymRatServiceImpl implements GymRatService {

    private final GymRatRepo gymRatRepository;

    @Autowired
    public GymRatServiceImpl(GymRatRepo grRepo) {
        this.gymRatRepository = grRepo;
    }

    @Override
    public List<GymRat> getAllGymRats() {
        return gymRatRepository.findAll();
    }

    @Override
    public void createGymRat(GymRatDto dto) {
        // TODO: add validation logic
        GymRat gymrat = new GymRat(dto);
        GymRatProfile gymRatProfile = new GymRatProfile(dto);
        gymrat.setProfile(gymRatProfile);
        gymRatRepository.save(gymrat);
    }

    @Override
    public GymRat getGymRatById(int id) {
        Optional<GymRat> gymRat = gymRatRepository.findById(id);
        return gymRat.orElse(null);
    }

    // updatedGymRat contains the updated info (must not contain the id)
    @Override
    public void updateGymRatById(int id, GymRat updatedGymRat) {
        // setting the id attribute make the save method merge into
        // updatedGymRat an existing record instead of creating a new one
        updatedGymRat.setId(id);
        gymRatRepository.save(updatedGymRat);
    }

    @Override
    public void deleteGymRatById(int id) {
        gymRatRepository.deleteById(id);
    }
}
