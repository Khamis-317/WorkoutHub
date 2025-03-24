package com.WorkoutHub.workout_hub.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id")
    private Integer id;

    @Column(name = "rest_time")
    private int restTime;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "exercise_info_id", nullable = false)
    private ExerciseInfo exerciseInfo;


    @ManyToOne(
            cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name =  "workout_id")
    private Workout workout;


    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name =  "routine_id")
    private Routine routine;


    @OneToMany(
            mappedBy = "exercise" ,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Set> sets;


    //region Adding, removing from/to list
    public void addExercise(Set theSet){
        if (sets == null){
            sets = new ArrayList<>();
        }
        theSet.setExercise(this);
        sets.add(theSet);
    }
    //endregion

}
