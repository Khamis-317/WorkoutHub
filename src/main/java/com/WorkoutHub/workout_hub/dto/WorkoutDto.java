package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Workout;
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
    private int durationInMinutes;
    private float totalVolume;
    private int numberOfSets;
    private List<ExerciseDto> exercises;

    public static WorkoutDto createDto(Workout workout, List<Exercise> exercises, boolean detailed) {
        int duration = (int) Duration.between(workout.getWorkoutpost().getStartTime(), workout.getWorkoutpost().getFinishTime()).toMinutes();

        List<ExerciseDto> exList = exercises.stream().map(
                (detailed)? ExerciseDto::createDtoWithSets : ExerciseDto::createSimpleDto
                ).toList();

        return WorkoutDto.builder()
                .totalVolume(workout.getTotalVolume())
                .id(workout.getId())
                .createdAt(workout.getCreatedAt())
                .durationInMinutes(duration)
                .exercises(exList)
                .numberOfSets(workout.getNumberOfSets())
                .workoutTitle(workout.getWorkoutpost().getTitle())
                .build();
    }
}
