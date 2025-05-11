package com.WorkoutHub.workout_hub.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseTemplateDto {
    private String name;
    private String instructions;
    private List<MuscleDto> muscleGroup;
}
