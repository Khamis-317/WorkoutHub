package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.ExerciseInfo;
import com.WorkoutHub.workout_hub.entity.Set;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExerciseDto {
    private String exerciseName;
    List<Set> sets;
    private int setsNum;
    private ExerciseInfo exerciseInfo;

    public static ExerciseDto createSimpleDto(Exercise exercise) {
        return ExerciseDto.builder()
                .exerciseName(exercise.getExerciseInfo().getName())
                .setsNum(exercise.getSets().size())
                .build();
    }

    public static ExerciseDto createDtoWithSets(Exercise exercise) {
        return ExerciseDto.builder()
                .exerciseName(exercise.getExerciseInfo().getName())
                .sets(exercise.getSets())
                .build();
    }
}
