package com.WorkoutHub.workout_hub.dto.response;



import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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
public class ExerciseHistoryDto {
    @JsonProperty("exercise_info_id")
    private Integer exerciseInfoId;

    @JsonProperty("exercise_name")
    private String exerciseName;

    @JsonProperty("last_performed")
    private OffsetDateTime lastPerformed;

    @JsonProperty("all_time_sets")
    private List<SetHistoryDto> allTimeSets;

}
