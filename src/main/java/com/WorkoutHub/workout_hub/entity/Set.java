package com.WorkoutHub.workout_hub.entity;


import com.WorkoutHub.workout_hub.enums.SetType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "reps")
    private int reps;

    @Column(name = "weight")
    private double weight;

    @Column(name = "set_type")
    private SetType setType;

    @Column(name = "is_completed")
    Boolean isCompleted;

    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name =  "exercise_id")
    private Exercise exercise;


}
