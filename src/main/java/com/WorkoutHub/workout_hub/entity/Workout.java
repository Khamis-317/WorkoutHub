package com.WorkoutHub.workout_hub.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Workout extends BaseEntity {

    @Column(name = "total_volume", nullable = false)
    private double totalVolume;

    @Column(name = "number_of_sets", nullable = false)
    private int numberOfSets;


    //region Relations with other entities

    @OneToOne(mappedBy = "workout", cascade = CascadeType.ALL)
    private WorkoutPost workoutpost;

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
    @NotEmpty
    private List<Exercise> exercises;
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
