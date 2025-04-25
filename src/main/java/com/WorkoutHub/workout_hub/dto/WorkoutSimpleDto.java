package com.WorkoutHub.workout_hub.dto;

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
    int volume;
    private int id;
    private LocalDateTime createdAt;
    private int durationInMinutes;
    private List<ExerciseSimpleDto> exercises;
    private String workoutTitle;

    public static WorkoutSimpleDto createDto(Workout workout) {
        int duration = (int) Duration.between(workout.getWorkoutpost().getStartTime(), workout.getWorkoutpost().getFinishTime()).toMinutes();
        System.out.println(duration);
        //List<ExerciseDto> exList =
        return WorkoutSimpleDto.builder()
                .id(workout.getId())
                .createdAt(workout.getCreatedAt())
                .durationInMinutes(duration)
                .workoutTitle(workout.getWorkoutpost().getTitle())
                .build();
    }
}
