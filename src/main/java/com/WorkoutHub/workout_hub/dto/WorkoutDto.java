package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.enums.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkoutDto {
    private UUID id;
    private String workoutTitle;
    private OffsetDateTime createdAt;
    int duration;
    private double totalVolume;
    private int numberOfSets;
    private Visibility visibility;
    private List<ExerciseDto> exercises;


}
