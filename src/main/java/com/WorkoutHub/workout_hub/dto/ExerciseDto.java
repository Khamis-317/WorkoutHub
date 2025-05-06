package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.ExerciseInfo;
import com.WorkoutHub.workout_hub.entity.Set;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private int exerciseInfoId;
    private String exerciseName;
    List<Set> sets;
    private int setsNum;
    private ExerciseInfo exerciseInfo;


}
