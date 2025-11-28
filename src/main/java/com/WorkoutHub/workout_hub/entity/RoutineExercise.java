package com.WorkoutHub.workout_hub.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routine_exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoutineExercise extends BaseEntity {

    @Column(name = "order_in_routine", nullable = false)
    private int orderInRoutine;

    @Column(name = "target_sets", nullable = false)
    private int targetSets;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "exercise_info_id", nullable = false)
    private ExerciseInfo exerciseInfo;

    @OneToMany(mappedBy = "routineExercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineSetTemplate> setTemplates;

    public void addSetTemplate(RoutineSetTemplate setTemplate) {
        if (setTemplates == null) {
            setTemplates = new ArrayList<>();
        }
        setTemplate.setRoutineExercise(this);
        setTemplates.add(setTemplate);
    }
}
