package com.WorkoutHub.workout_hub.dto.response;

import com.WorkoutHub.workout_hub.enums.SetType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetLookupEntryDto {
    private Integer position;

    @JsonProperty("set_type")
    private SetType setType;

    private Integer reps;

    private Double weight;
}
