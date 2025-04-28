package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.validator.UniqueEmail;
import com.WorkoutHub.workout_hub.validator.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class GymRatRequestDto {
    // gymrat user data
    @NotNull
    @NotBlank
    @UniqueUsername
    private String username;
    @NotNull
    @NotBlank
    @Email
    @UniqueEmail
    private String email;
    @NotNull
    @NotBlank
    private String password;    // TODO: add a password pattern validation

    // gymrat profile data
    private String firstName;
    private String lastName;
    private String country;
    private LocalDate birthDate;
    private String bio;
}
