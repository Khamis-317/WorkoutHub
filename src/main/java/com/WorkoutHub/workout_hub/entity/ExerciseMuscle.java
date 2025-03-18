package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.enums.MuscleImportance;
import jakarta.persistence.*;
import lombok.*;

// ExerciseMuscle represents the Hibernate mapping
// of the join table between exercise_info and muscle
// with extra attribute -> muscle_importance
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "exercise_muscles")
public class ExerciseMuscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH,
            }
    )
    @JoinColumn(name = "exercise_info_id")
    private ExerciseInfo exercise;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.DETACH,
            }
    )
    @JoinColumn(name = "muscle_id")
    private Muscle muscle;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "muscle_importance")
    private MuscleImportance importance;
}
