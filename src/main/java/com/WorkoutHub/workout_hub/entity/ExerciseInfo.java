package com.WorkoutHub.workout_hub.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "exercise_info")
public class ExerciseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "instructions")
    String instructions;

    // saving an (exercise) with a muscle group will also save the (muscles) in their table
    // deleting an (exercise) doesn't delete the (muscles) in the muscle group
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "exercise_muscles",
            joinColumns = @JoinColumn(name = "exercise_info_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_id")
    )
    private List<Muscle> muscleGroup;

    // convenience methods
    public void addMuscle(Muscle muscle) {
        if (muscleGroup == null) {
            muscleGroup = new ArrayList<>();
        }
        muscleGroup.add(muscle);
        // muscle.addExercise(this);
    }


//    public void addExerciseMuscle(Muscle muscle, boolean isPrimary) {
//        if (muscleGroup == null) {
//            muscleGroup = new ArrayList<>();
//        }
//        ExerciseMuscle exerciseMuscle = ExerciseMuscle
//                .builder()
//                .muscle(muscle)
//                .exercise(this)
//                .isPrimary(isPrimary)
//                .build();
//        muscleGroup.add(exerciseMuscle);
//    }
}