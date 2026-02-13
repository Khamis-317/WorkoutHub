package com.WorkoutHub.workout_hub.dto.request;


import com.WorkoutHub.workout_hub.enums.SetType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetDto {
    @NotNull(message = "Set UUID is required")
    @JsonProperty("set_uuid")
    private UUID setUuid;

    @NotNull (message = "Set number is required")
    @Min(value = 1, message =  "Set number must be at least 1")
    @JsonProperty ("set_number")
    private Integer setNumber;

    @NotNull (message = "Set type is required")
    @JsonProperty("set_type")
    private SetType setType;

    @NotNull(message = "Reps is required")
    @Min(value = 0, message = "Reps cannot be negative")
    private Integer reps;


    @NotNull(message = "Weight is required")
    @Min(value = 0, message = "Weight cannot be negative")
    private Double weight;


}
