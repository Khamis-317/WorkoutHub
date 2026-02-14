package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.auth.RegisterRequest;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GymRatService {

    private final GymRatRepo gymRatRepository;

    @Autowired
    public GymRatService(GymRatRepo grRepo) {
        this.gymRatRepository = grRepo;
    }


    public List<UserDto> getAllGymRats() {
        return gymRatRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }


    public void createGymRat(RegisterRequest creationDto) {
        GymRat gymrat = GymRat.builder()
                .username(creationDto.getUsername())
                .email(creationDto.getEmail())
                .password(creationDto.getPassword())
                .build();

        GymRatProfile gymRatProfile = GymRatProfile.builder()
                .firstName(creationDto.getFirstName())
                .lastName(creationDto.getLastName())
                .country(creationDto.getCountry())
                .birthDate(creationDto.getBirthDate())
                .bio(creationDto.getBio())
                .build();

        gymrat.setProfile(gymRatProfile);
        gymRatRepository.save(gymrat);
    }


    public UserDto getGymRatById(UUID id) {
        GymRat gymRat = gymRatRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Entity Not Found: Gymrat with id: " + id + " is not found."));
        return new UserDto(gymRat);
    }


    public ProfileDto getGymRatProfileById(UUID id) {
        GymRat gymRat = gymRatRepository.findGymRatWithProfileById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity Not Found: Gymrat with id: " + id + " is not found."));
        return new ProfileDto(gymRat);
    }


    public ProfileDto updateGymRatById(UUID id, GymRatUpdateDto updateDto) throws EntityNotFoundException {
        GymRat gymrat = gymRatRepository.findGymRatWithProfileById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Entity Not Found: Gymrat with id: " + id + " is not found."));
        
        // update gymrat user data
        if (updateDto.getUsername() != null) gymrat.setUsername(updateDto.getUsername());
        if (updateDto.getEmail() != null) gymrat.setEmail(updateDto.getEmail());

        // update gymrat profile data
        GymRatProfile profile = gymrat.getProfile();
        if (profile != null) {
            if (updateDto.getFirstName() != null) profile.setFirstName(updateDto.getFirstName());
            if (updateDto.getLastName() != null) profile.setLastName(updateDto.getLastName());
            if (updateDto.getCountry() != null) profile.setCountry(updateDto.getCountry());
            if (updateDto.getBirthDate() != null) profile.setBirthDate(updateDto.getBirthDate());
            if (updateDto.getBio() != null) profile.setBio(updateDto.getBio());
        }

        gymRatRepository.save(gymrat);
        return new ProfileDto(gymrat);
    }


    public void deleteGymRatById(UUID id) {
        boolean gymratExists = gymRatRepository.existsById(id);
        if(!gymratExists)
            throw new EntityNotFoundException("Entity Not Found: Gymrat with id: " + id + " is not found.");
        gymRatRepository.deleteById(id);
    }
}
