package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.enums.Visibility;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "routine_workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WorkoutRoutine extends Workout{

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private Visibility visibility;

    @Override
    public String toString() {
        return "WorkoutRoutine{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", visibility=" + visibility +
                ", gymRatID=" + gymRat.getId() +
                '}';
    }
}
