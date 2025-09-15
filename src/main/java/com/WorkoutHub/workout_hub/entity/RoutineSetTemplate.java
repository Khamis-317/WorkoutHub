package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.enums.SetType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "routine_set_templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineSetTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "set_order", nullable = false)
    private int setOrder;

    @Column(name = "target_reps")
    private Integer targetReps;

    @Column(name = "target_weight")
    private Double targetWeight;

    @Column(name = "set_type")
    @Enumerated(EnumType.STRING)
    private SetType setType;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "routine_exercise_id", nullable = false)
    private RoutineExercise routineExercise;
}
