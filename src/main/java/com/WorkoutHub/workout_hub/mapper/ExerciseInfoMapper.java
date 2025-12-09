package com.WorkoutHub.workout_hub.mapper;

import com.WorkoutHub.workout_hub.dto.response.ExerciseInfoDto;
import com.WorkoutHub.workout_hub.dto.response.MuscleDto;
import com.WorkoutHub.workout_hub.entity.ExerciseInfo;
import com.WorkoutHub.workout_hub.entity.ExerciseMuscle;
import com.WorkoutHub.workout_hub.entity.Muscle;
import com.WorkoutHub.workout_hub.enums.MuscleImportance;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseInfoMapper {

    MuscleDto toMuscleDto(Muscle muscle);

    List<MuscleDto> toMuscleDtoList(List<Muscle> muscles);

    @Mapping(target = "primaryMuscles", ignore = true)
    @Mapping(target = "secondaryMuscles", ignore = true)
    ExerciseInfoDto toExerciseInfoDto(ExerciseInfo exerciseInfo);


    List<ExerciseInfoDto> toExerciseInfoDtoList(List<ExerciseInfo> exercises);

    @AfterMapping
    default void mapMuscleGroup(ExerciseInfo source, @MappingTarget ExerciseInfoDto target){
        List<ExerciseMuscle> groups = source.getMuscleGroup();

        if (groups == null || groups.isEmpty()) {
            target.setPrimaryMuscles(List.of());
            target.setSecondaryMuscles(List.of());
            return;
        }

        List<Integer> primary = new ArrayList<>(groups.size());
        List<Integer> secondary = new ArrayList<>(groups.size());

        for (ExerciseMuscle em : groups) {
            if (em.getMuscle() == null) continue;

            if (em.getImportance() == MuscleImportance.PRIMARY) {
                primary.add(em.getMuscle().getId());
            } else {
                secondary.add(em.getMuscle().getId());
            }
        }
        target.setPrimaryMuscles(primary);
        target.setSecondaryMuscles(secondary);
    }
}
