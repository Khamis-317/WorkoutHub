package com.WorkoutHub.workout_hub.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

public class ExerciseHistoryResponse {

    @JsonProperty("last_sync")
    private OffsetDateTime lastSync;

    @JsonProperty("exercise_history")
    private List<ExerciseHistoryDto> exerciseHistory;
}
