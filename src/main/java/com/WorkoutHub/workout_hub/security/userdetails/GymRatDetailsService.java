package com.WorkoutHub.workout_hub.security.userdetails;

import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GymRatDetailsService implements UserDetailsService {

    private final GymRatRepo gymRatRepo;

    @Override
    public UserDetails loadUserByUsername(String loginIdentifier) throws UsernameNotFoundException {
        return gymRatRepo.findGymRatByUsernameOrEmail(loginIdentifier, loginIdentifier)
                .map(GymRatDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(loginIdentifier));
    }
}
