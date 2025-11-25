package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.GymRat;
import com.WorkoutHub.workout_hub.entity.Workout;
import com.WorkoutHub.workout_hub.entity.WorkoutPost;
import com.WorkoutHub.workout_hub.enums.Visibility;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class WorkoutCreationDto {
    @NotNull
    private float totalVolume;

    @NotNull
    private int numberOfSets;

    @NotNull
    private String title;

    private String caption;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private int duration;

    @NotNull
    private Visibility visibility;

    @NotEmpty
    private List<ExerciseDto> exercises;


}
