package com.WorkoutHub.workout_hub.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 420, message = "Invalid Username or Email must be between 3 and 20 characters")
    private String loginIdentifier;


    @NotNull
    @Size(min = 8, max = 128 ,message = "Password must be at least 8 characters long")
    private String password;
}
