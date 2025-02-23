package com.WorkoutHub.workout_hub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "muscles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "muscle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExerciseMuscle> activationExercises;
}
