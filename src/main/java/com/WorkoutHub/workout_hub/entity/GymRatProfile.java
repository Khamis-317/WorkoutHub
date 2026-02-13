package com.WorkoutHub.workout_hub.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "gym_rat_profiles")
public class GymRatProfile extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "country")
    private String country;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "bio")
    private String bio;
}
