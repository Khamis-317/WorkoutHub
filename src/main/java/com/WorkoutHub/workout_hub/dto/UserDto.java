package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.GymRat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class UserDto {
    // gymrat user data
    private Integer id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

    public UserDto(GymRat gymrat) {
        this.id = gymrat.getId();
        this.username = gymrat.getUsername();
        this.email = gymrat.getEmail();
        this.createdAt = gymrat.getCreatedAt();
    }
}
