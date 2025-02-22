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

    private int exercise_id;

    private int muscle_id;
}
