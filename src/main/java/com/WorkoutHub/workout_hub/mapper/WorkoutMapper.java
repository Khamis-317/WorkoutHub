package com.WorkoutHub.workout_hub.mapper;
import com.WorkoutHub.workout_hub.dto.request.SetDto;
import com.WorkoutHub.workout_hub.dto.response.ExerciseHistorySummaryDto;
import com.WorkoutHub.workout_hub.dto.response.SetHistoryDto;
import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    /**
     * Map SetDto (request) to Set entity.
     * The exercise relationship and ID will be set manually in the service layer.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "exercise", ignore = true)
    Set toEntity(SetDto setDto);


    /**
     * Map Set entity to SetHistoryDto (response).
     */
    @Mapping(target = "performedAt", source = "createdAt")
    SetHistoryDto  toHistoryDto(Set set);


    List<SetHistoryDto> toHistoryDtoList(List<Set> sets);

    /**
     * Map Exercise entity to ExerciseHistorySummaryDto.
     */

    @Mapping(target = "exerciseInfoId", source = "exerciseInfo.id")
    @Mapping(target = "exerciseName", source = "exerciseInfo.name")
    @Mapping(target = "sets", source = "sets")
    ExerciseHistorySummaryDto toExerciseHistorySummary(Exercise exercise);


    List<ExerciseHistorySummaryDto> toExerciseHistorySummaryList(List<Exercise> exercises);


}
