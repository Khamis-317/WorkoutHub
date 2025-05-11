package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.enums.MuscleImportance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MuscleDto {
    private String muscleName;
    private MuscleImportance muscleImportance;    // may change type to String
}
