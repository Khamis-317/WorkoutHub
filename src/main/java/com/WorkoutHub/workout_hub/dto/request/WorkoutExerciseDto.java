package com.WorkoutHub.workout_hub.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
public class WorkoutExerciseDto {
    @NotNull(message = "Exercise Id is required")
    @JsonProperty("exercise_id")
    private Long exerciseId;

    @NotNull(message = "Order in workout is required")
    @Min(value = 1, message = "order mest be at least 1")
    @JsonProperty("order_in_workout")
    private Integer orderInWorkout;

    @NotEmpty(message = "Exercise must contain at least one set")
    @Valid
    private List<SetDto> sets;
}
