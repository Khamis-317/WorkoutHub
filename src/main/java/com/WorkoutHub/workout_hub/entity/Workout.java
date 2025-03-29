package com.WorkoutHub.workout_hub.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //region Relations with other entities

    @OneToOne(mappedBy = "workout")
    WorkoutPost workoutpost;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "gym_rat_id")
    private GymRat gymRat;

    //Could be Eager, Lazy for Posts
    @OneToMany(
            mappedBy = "workout" ,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Exercise> exercises;
    //endregion


    //region Adding, removing from/to list
    public void addExercise(Exercise theExercise){
        if (exercises == null){
            exercises = new ArrayList<>();
        }
        theExercise.setWorkout(this);
        exercises.add(theExercise);
    }
    //endregion

}
