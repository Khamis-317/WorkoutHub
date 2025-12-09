package com.WorkoutHub.workout_hub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseLibraryResponse {
    private List<MuscleDto> muscles;
    private List<ExerciseInfoDto> exercises;

}
