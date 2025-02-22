package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.enums.Visibillity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "routine_workouts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WorkoutRoutine extends Workout{

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private Visibillity visibillity;
}
