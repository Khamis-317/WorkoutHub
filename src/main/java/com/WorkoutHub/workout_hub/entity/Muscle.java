package com.WorkoutHub.workout_hub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "muscles", uniqueConstraints = {
@UniqueConstraint(name = "UniqueMuscleName", columnNames = {"name"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "muscle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExerciseMuscle> activationExercises;
}
