package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.GymRatRequestDto;
import com.WorkoutHub.workout_hub.dto.UserDto;
import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.entity.GymRatProfile;
import com.WorkoutHub.workout_hub.exception.GymRatNotFoundException;
import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GymRatServiceImpl implements GymRatService {

    private final GymRatRepo gymRatRepository;

    @Autowired
    public GymRatServiceImpl(GymRatRepo grRepo) {
        this.gymRatRepository = grRepo;
    }

    @Override
    public List<UserDto> getAllGymRats() {
        return gymRatRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void createGymRat(GymRatRequestDto creationDto) {
        GymRat gymrat = new GymRat(creationDto);
        GymRatProfile gymRatProfile = new GymRatProfile(creationDto);
        gymrat.setProfile(gymRatProfile);
        gymRatRepository.save(gymrat);
    }

    @Override
    public UserDto getGymRatById(int id) {
        Optional<GymRat> gymRat = gymRatRepository.findById(id);
        if(gymRat.isPresent()) {
            return new UserDto(gymRat.get());
        }
        throw new GymRatNotFoundException("Resource Not Found: Gymrat with id: " + id + " is not found.");
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
