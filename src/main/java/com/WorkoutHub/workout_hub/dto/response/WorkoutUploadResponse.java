package com.WorkoutHub.workout_hub.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
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
public class WorkoutUploadResponse {
    @JsonProperty("workout_id")
    private UUID workoutId;


    @JsonProperty("synced_at")
    private OffsetDateTime syncedAt;

    @JsonProperty("total_volume")
    private Double totalVolume;

    @JsonProperty("number_of_sets")
    private Integer numberOfSets;

    @JsonProperty("new_exercise_history")
    private List<ExerciseHistoryDto> newExerciseHistory;
}
