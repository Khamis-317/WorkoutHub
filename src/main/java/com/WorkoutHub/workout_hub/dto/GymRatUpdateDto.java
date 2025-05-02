package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.validator.UniqueEmail;
import com.WorkoutHub.workout_hub.validator.UniqueUsername;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GymRatUpdateDto {
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

    // gymrat profile data
    @Size(max=64)
    private String firstName;
    @Size(max=64)
    private String lastName;
    @Size(max=64)
    private String country;
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;    // birthdate must be a past date and in (yyyy-MM-dd) format
    private String bio;
}
