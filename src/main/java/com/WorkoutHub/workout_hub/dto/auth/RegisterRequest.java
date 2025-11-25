package com.WorkoutHub.workout_hub.dto.auth;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 28, message = "Username must be between 3 and 28 characters")
    private String username;

    @NotNull(message = "Email is required")
    @Email(message = "Email must be a valid format")
    private String email;

    @NotNull
    @Size(min = 8, max = 128 ,message = "Password must be at least 8 characters long")
    // Todo: add @Pattern later for Regex (UpperCase, Special Char, etc.)
    private String password;


    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    private String country;

    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    private String bio;
}
