package com.WorkoutHub.workout_hub.dto.response;



import com.WorkoutHub.workout_hub.enums.SetType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetHistoryDto {

    @JsonProperty("set_number")
    private Integer setNumber;


    @JsonProperty("set_type")
    private SetType setType;


    private Integer reps;

    private Double weight;

    @JsonProperty("performed_at")
    private OffsetDateTime performedAt;

}
