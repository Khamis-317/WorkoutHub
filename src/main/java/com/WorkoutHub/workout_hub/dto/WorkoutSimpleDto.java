package com.WorkoutHub.workout_hub.dto;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Workout;
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
public class WorkoutSimpleDto {
    private int id;
    private String workoutTitle;
    private LocalDateTime createdAt;
    private int durationInMinutes;
    float totalVolume;
    private List<ExerciseSimpleDto> exercises;

    public static WorkoutSimpleDto createDto(Workout workout, List<Exercise> exercises) {
        int duration = (int) Duration.between(workout.getWorkoutpost().getStartTime(), workout.getWorkoutpost().getFinishTime()).toMinutes();
        List<ExerciseSimpleDto> exList = exercises.stream().map(ExerciseSimpleDto::createDto).toList();
        return WorkoutSimpleDto.builder()
                .totalVolume(workout.getTotalVolume())
                .id(workout.getId())
                .createdAt(workout.getCreatedAt())
                .durationInMinutes(duration)
                .exercises(exList)
                .workoutTitle(workout.getWorkoutpost().getTitle())
                .build();
    }
}
