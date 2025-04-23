package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.entity.GymRatProfile;
import lombok.NonNull;

import java.time.LocalDate;

public class ProfileDto {
    // gymrat user data
    @NonNull
    private String username;
    @NonNull
    private String email;

    // gymrat profile data
    private String firstName;
    private String lastName;
    private String country;
    private LocalDate birthDate;
    private String bio;

    public ProfileDto(GymRat gymrat, GymRatProfile profile) {
        this.username = gymrat.getUsername();
        this.email = gymrat.getEmail();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.country = profile.getCountry();
        this.birthDate = profile.getBirthDate();
        this.bio = profile.getBio();
    }
}
