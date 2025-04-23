package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.GymRat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class GymRatCreationDto {
    // gymrat user data
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;

    // gymrat profile data
    private String firstName;
    private String lastName;
    private String country;
    private LocalDate birthDate;
    private String bio;
}
