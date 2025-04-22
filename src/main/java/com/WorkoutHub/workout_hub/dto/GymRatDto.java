package com.WorkoutHub.workout_hub.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class GymRatDto {
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String country;
    @NonNull
    private LocalDate birthDate;

    private String bio = "";
}
