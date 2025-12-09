package com.WorkoutHub.workout_hub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseInfoDto implements Serializable {
    private Integer id;
    private String name;
    private String instructions;
    private List<Integer> primaryMuscles;
    private List<Integer> secondaryMuscles;
}
