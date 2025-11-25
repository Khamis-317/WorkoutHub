package com.WorkoutHub.workout_hub.entity;


import com.WorkoutHub.workout_hub.enums.SetType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Set extends BaseEntity {

    @Column(name = "set_number", nullable = false)
    private int setNumber;


    @Column(name = "reps", nullable = false)
    private int reps;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "set_type") // cannot be null or could be normal by default
    @Enumerated(value = EnumType.STRING)
    private SetType setType;

    @JsonIgnore
    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name =  "exercise_id")
    private Exercise exercise;


}
