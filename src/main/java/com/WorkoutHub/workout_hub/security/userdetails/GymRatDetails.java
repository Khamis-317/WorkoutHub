package com.WorkoutHub.workout_hub.security.userdetails;

import com.WorkoutHub.workout_hub.entity.GymRat;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class GymRatDetails implements UserDetails {
    private final GymRat gymRat;
    @Override
    public String getUsername() {
        return gymRat.getUsername();
    }
    @Override
    public String getPassword() {
        return gymRat.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
