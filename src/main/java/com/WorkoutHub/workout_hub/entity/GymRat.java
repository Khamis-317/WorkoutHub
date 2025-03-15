package com.WorkoutHub.workout_hub.entity;

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
    private int id;

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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "gym_rat_profile_id", nullable = false)
    private GymRatProfile info;

    @OneToMany(mappedBy = "gymRat", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Workout> workouts;

    @OneToMany(mappedBy = "gymRat", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Routine> routines;
    //endregion

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
