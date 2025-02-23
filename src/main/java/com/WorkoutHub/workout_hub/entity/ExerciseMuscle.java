package com.WorkoutHub.workout_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exercise_muscles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseMuscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "is_primary")
    private boolean isPrimary;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE,
                CascadeType.DETACH,
                CascadeType.REFRESH
    })
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
    })
    @JoinColumn(name = "muscle_id")
    private Muscle muscle;
}
