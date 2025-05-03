package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.GymRatCreationDto;
import com.WorkoutHub.workout_hub.dto.GymRatUpdateDto;
import com.WorkoutHub.workout_hub.dto.ProfileDto;
import com.WorkoutHub.workout_hub.dto.UserDto;
import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.entity.GymRatProfile;
import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void createGymRat(GymRatCreationDto creationDto) {
        GymRat gymrat = new GymRat(creationDto);
        GymRatProfile gymRatProfile = new GymRatProfile(creationDto);
        gymrat.setProfile(gymRatProfile);
        gymRatRepository.save(gymrat);
    }

    @Override
    public UserDto getGymRatById(int id) {
        GymRat gymRat = gymRatRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Entity Not Found: Gymrat with id: " + id + " is not found."));
        return new UserDto(gymRat);
    }

    @Override
    public ProfileDto getGymRatProfileById(int id) {
        GymRat gymRat = gymRatRepository.findGymRatWithProfileById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found: Gymrat with id: " + id + " is not found."));
        return new ProfileDto(gymRat);
    }

    @Override
    public ProfileDto updateGymRatById(int id, GymRatUpdateDto updateDto) throws EntityNotFoundException {
        GymRat gymrat = gymRatRepository.findGymRatWithProfileById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Entity Not Found: Gymrat with id: " + id + " is not found."));
        gymrat.update(updateDto);
        gymRatRepository.save(gymrat);
        return new ProfileDto(gymrat);
    }

    @Override
    public void deleteGymRatById(int id) {
        boolean gymratExists = gymRatRepository.existsById(id);
        if(!gymratExists)
            throw new EntityNotFoundException("Entity Not Found: Gymrat with id: " + id + " is not found.");
        gymRatRepository.deleteById(id);
    }
}
