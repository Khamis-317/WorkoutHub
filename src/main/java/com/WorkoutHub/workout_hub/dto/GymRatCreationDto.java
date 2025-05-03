package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.validator.UniqueEmail;
import com.WorkoutHub.workout_hub.validator.UniqueUsername;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GymRatCreationDto {
    // gymrat user data
    @NotNull
    @NotBlank
    @Size(max=64)
    @UniqueUsername
    private String username;
    @NotNull
    @NotBlank
    @Size(max=320)
    @Email
    @UniqueEmail
    private String email;
    @NotNull
    @NotBlank
    @Size(max=128)
    private String password;    // TODO: add a password pattern validation

    // gymrat profile data
    private String firstName;
    private String lastName;
    private String country;
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;    // birthdate must be a past date and in (yyyy-MM-dd) format
    private String bio;
}
