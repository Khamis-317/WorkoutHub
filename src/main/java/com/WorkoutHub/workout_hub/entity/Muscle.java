package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.enums.MuscleImportance;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "muscles")
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "muscle",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<ExerciseMuscle> activationExercises;
}
