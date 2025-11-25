package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Workout;
import com.WorkoutHub.workout_hub.enums.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutDto {
    private int id;
    private String workoutTitle;
    private LocalDateTime createdAt;
    int duration;
    private float totalVolume;
    private int numberOfSets;
    private Visibility visibility;
    private List<ExerciseDto> exercises;


}
