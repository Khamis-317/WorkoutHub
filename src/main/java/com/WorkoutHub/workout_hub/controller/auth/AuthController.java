package com.WorkoutHub.workout_hub.controller.auth;

import com.WorkoutHub.workout_hub.dto.auth.AuthResponse;
import com.WorkoutHub.workout_hub.dto.auth.LoginRequest;
import com.WorkoutHub.workout_hub.dto.auth.RegisterRequest;
import com.WorkoutHub.workout_hub.response.GenericResponse;
import com.WorkoutHub.workout_hub.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        var authResponse = authService.register(registerRequest);
        return ResponseEntity.ok(GenericResponse.success(authResponse));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        var authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(GenericResponse.success(authResponse));
    }


}
