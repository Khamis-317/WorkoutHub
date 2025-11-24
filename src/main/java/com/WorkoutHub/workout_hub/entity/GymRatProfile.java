package com.WorkoutHub.workout_hub.entity;


import com.WorkoutHub.workout_hub.dto.auth.RegisterRequest;
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
public class GymRatProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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

    public GymRatProfile(RegisterRequest dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.country = dto.getCountry();
        this.birthDate = dto.getBirthDate();
        this.bio = dto.getBio();
    }
}
