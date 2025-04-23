package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.GymRatCreationDto;
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

@Service
public class GymRatServiceImpl implements GymRatService {

    private final GymRatRepo gymRatRepository;

    @Autowired
    public GymRatServiceImpl(GymRatRepo grRepo) {
        this.gymRatRepository = grRepo;
    }

    @Override
    public List<UserDto> getAllGymRats() {
        List<UserDto> dtos = new ArrayList<>();
        List<GymRat> gymrats = gymRatRepository.findAll();
        for(GymRat gymrat : gymrats) {
            UserDto dto = new UserDto(gymrat);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void createGymRat(GymRatCreationDto creationDto) {
        // TODO: add validation logic (check existing users)
        GymRat gymrat = new GymRat(creationDto);
        GymRatProfile gymRatProfile = new GymRatProfile(creationDto);
        gymrat.setProfile(gymRatProfile);
        gymRatRepository.save(gymrat);
    }

    @Override
    public UserDto getGymRatById(int id) {
        UserDto dto;
        Optional<GymRat> gymRat = gymRatRepository.findById(id);
        if(gymRat.isPresent()) {
            return new UserDto(gymRat.get());
        }
        throw new GymRatNotFoundException("Gym rat with id: " + id + " is not found.");
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
