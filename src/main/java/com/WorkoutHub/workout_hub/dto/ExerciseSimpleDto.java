package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseSimpleDto {
    private String exerciseName;
    private int setsNum;

    public static ExerciseSimpleDto createDto(Exercise exercise) {
        return ExerciseSimpleDto.builder()
                .exerciseName(exercise.getExerciseInfo().getName())
                .setsNum(exercise.getSets().size())
                .build();
    }
}
