package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.enums.MuscleImportance;
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
    @OneToMany(
            mappedBy = "exercise",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<ExerciseMuscle> muscleGroup;


    //deleting ex_info would delete all instances
    @OneToMany(
            mappedBy = "exerciseInfo" ,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Exercise> exercises;


    // convenience methods
    public void addMuscle(Muscle muscle, MuscleImportance importance) {
        if (muscleGroup == null) {
            muscleGroup = new ArrayList<>();
        }
        ExerciseMuscle exerciseMuscle = ExerciseMuscle
                .builder()
                .exercise(this)
                .muscle(muscle)
                .importance(importance)
                .build();
        muscleGroup.add(exerciseMuscle);
    }


    public void addExercise(Exercise theExercise){
        if (exercises == null){
            exercises = new ArrayList<>();
        }
        theExercise.setExerciseInfo(this);
        exercises.add(theExercise);
    }
}

//    public void addMuscle(Muscle muscle) {
//        if (muscleGroup == null) {
//            muscleGroup = new ArrayList<>();
//        }
//        muscleGroup.add(muscle);
//        // muscle.addExercise(this);
//    }