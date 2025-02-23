package com.WorkoutHub.workout_hub.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;

@Entity
@Table(name = "exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "instructions", nullable = false)
    String instructions;

    // saving an (exercise) with a muscle group will also save the (muscles) in their table
    // deleting an (exercise) doesn't delete the (muscles) in the muscle group
    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExerciseMuscle> muscleGroup;

    // convenience methods
    public void addExerciseMuscle(Muscle muscle, boolean isPrimary) {
        if (muscleGroup == null) {
            muscleGroup = new ArrayList<>();
        }
        ExerciseMuscle exerciseMuscle = ExerciseMuscle
                .builder()
                .muscle(muscle)
                .exercise(this)
                .isPrimary(isPrimary)
                .build();
        muscleGroup.add(exerciseMuscle);
    }
}

// Uni-directional OneToMany
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "exercise_id")
//    private Set<ExerciseMuscle> muscle_group;