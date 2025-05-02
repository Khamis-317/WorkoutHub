package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.dto.GymRatCreationDto;
import com.WorkoutHub.workout_hub.dto.GymRatUpdateDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "gym_rats")
public class GymRat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //region Relations with other entities
    // lazy fetch is used to get profile data only on demand
    // foreign key is put on the principal side (gym_rat)
    // to enforce the uni-directional relationship
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_rat_profile_id")
    private GymRatProfile profile;

    @OneToMany(mappedBy = "gymRat", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Workout> workouts;

    @OneToMany(mappedBy = "gymRat", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Routine> routines;
    //endregion

    public GymRat(GymRatCreationDto dto) {
        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.password = dto.getPassword();  // TODO: saving plain password (encryption needed)
    }

    public void update(GymRatUpdateDto dto) {
        // update gymrat user data
        this.username = dto.getUsername() != null ? dto.getUsername() : this.username;
        this.email = dto.getEmail() != null ? dto.getEmail() : this.email;
        // update gymrat profile data
        this.profile.setFirstName(dto.getFirstName() != null ? dto.getFirstName() : this.profile.getFirstName());
        this.profile.setLastName(dto.getLastName() != null ? dto.getLastName() : this.profile.getLastName());
        this.profile.setCountry(dto.getCountry() != null ? dto.getCountry() : this.profile.getCountry());
        this.profile.setBirthDate(dto.getBirthDate() != null ? dto.getBirthDate() : this.profile.getBirthDate());
        this.profile.setBio(dto.getBio() != null ? dto.getBio() : this.profile.getBio());
    }

    //region Adding, removing from/to list
    public void addWorkout(Workout theWorkout){
        if (workouts == null)
            workouts = new ArrayList<>();
        theWorkout.setGymRat(this);
        workouts.add(theWorkout);
    }

    public void removeWorkout(Workout theWorkout){
        workouts.remove(theWorkout);
        theWorkout.setGymRat(null);
    }


    public void addRoutine(Routine theRoutine){
        if (routines == null)
            routines = new ArrayList<>();
        theRoutine.setGymRat(this);
        routines.add(theRoutine);
    }

    public void removeRoutine(Routine theRoutine){
        routines.remove(theRoutine);
        theRoutine.setGymRat(null);
    }
    //endregion
}
