package com.WorkoutHub.workout_hub.security;

import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import com.WorkoutHub.workout_hub.service.GymRatService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class GymRatDetailsService implements UserDetailsService {

    private final GymRatRepo gymRatRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return gymRatRepo.findGymRatByUsername(username)
                .map(GymRatDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }
}
