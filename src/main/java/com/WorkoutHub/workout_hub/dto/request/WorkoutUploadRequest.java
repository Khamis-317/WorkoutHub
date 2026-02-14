package com.WorkoutHub.workout_hub.dto.request;

import com.WorkoutHub.workout_hub.enums.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class WorkoutUploadRequest {
    @NotNull(message = "Workout ID is required")
    @JsonProperty("workout_id")
    private UUID workoutId;

    @NotNull(message = "Workout post ID is required")
    @JsonProperty("workout_post_id")
    private UUID workoutPostId;


    @NotNull(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @Size(max = 300, message = "Caption cannot exceed 300 characters")
    private String caption;

    @NotNull(message = "Visibility is required")
    private Visibility visibility;

    @NotNull(message = "Performed at timestamp is required")
    @JsonProperty("performed_at")
    private OffsetDateTime performedAt;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;

    @NotEmpty(message = "Workout must contain at least one exercise")
    @Valid
    private List<WorkoutExerciseDto> exercises;
}
