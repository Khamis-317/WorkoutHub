package com.WorkoutHub.workout_hub.dto.request;

import com.WorkoutHub.workout_hub.enums.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutUploadRequest {

    @NotNull(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 300, message = "Caption cannot exceed 300 characters")
    private String caption;

    @NotNull(message = "Visibility is required")
    private Visibility visibility;


    @NotNull(message = "Started at timestamp is required")
    @JsonProperty("started_at")
    private OffsetDateTime startedAt;

    @NotNull(message = "Completed at timestamp is required")
    @JsonProperty("completed_at")
    private  OffsetDateTime completedAt;

    @NotEmpty(message = "Workout must contain at least one exercise")
    @Valid
    private List<WorkoutExerciseDto> exercises;
}
