package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.enums.MuscleImportance;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "importance")
    private MuscleImportance importance;

    @ManyToMany(mappedBy = "muscleGroup",
            fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE,
                CascadeType.DETACH,
                CascadeType.REFRESH
    })
    private List<ExerciseInfo> activationExercises;
}
