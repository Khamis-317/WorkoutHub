package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.GymRat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class UserDto {
    // gymrat user data
    @NonNull
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String email;

    public UserDto(GymRat gymrat) {
        this.id = gymrat.getId();
        this.username = gymrat.getUsername();
        this.email = gymrat.getEmail();
    }
}
