package com.WorkoutHub.workout_hub.service.auth;

import com.WorkoutHub.workout_hub.dto.auth.AuthResponse;
import com.WorkoutHub.workout_hub.dto.auth.LoginRequest;
import com.WorkoutHub.workout_hub.dto.auth.RegisterRequest;
import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.entity.GymRatProfile;
import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import com.WorkoutHub.workout_hub.security.jwt.JwtService;
import com.WorkoutHub.workout_hub.security.userdetails.GymRatDetails;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.WorkoutHub.workout_hub.exception.DuplicateResourceException;
import org.springframework.security.authentication.BadCredentialsException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    private final GymRatRepo gymRatRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    /**
     * Registers a new user and creates their associated profile transactionally.
     * @param request The registration data (user credentials and basic profile info)
     * @return AuthResponse containing the JWT and User ID
     * @throws DuplicateResourceException if email/username already exists
     */
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (gymRatRepo.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }
        if(gymRatRepo.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username is already in use");
        }
        var profile = new GymRatProfile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setCountry(request.getCountry());
        profile.setBirthDate(request.getBirthDate());
        profile.setBio(request.getBio());

        var user = new GymRat();
        user.setProfile(profile);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        gymRatRepo.save(user);

        return generateAuthResponse(user);
    }


    /**
     * Authenticates a user using either their Email or Username.
     * @param request Contains loginIdentifier (email OR username) and password
     * @return AuthResponse containing the JWT
     * @throws BadCredentialsException if authentication fails
     */
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLoginIdentifier(),
                        request.getPassword()
                )
        );

        var user = gymRatRepo.findGymRatByUsernameOrEmail(
                request.getLoginIdentifier(),
                request.getLoginIdentifier()
        ).orElseThrow();

        return generateAuthResponse(user);
    }


    private AuthResponse generateAuthResponse(GymRat user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", String.valueOf(user.getId()));
        claims.put("role", "USER"); //TODO: Refactor to use Role Enum
        var userDetails = new GymRatDetails(user);

        String jwtToken = jwtService.generateToken(userDetails, claims);

        return AuthResponse.builder()
                .accessToken(jwtToken)
                .userId(user.getId().toString())
                .userName(user.getUsername()) //TODO: Add refresh token
                .expiresIn(jwtService.getJwtExpiration() / 1000)
                .build();
    }
}
