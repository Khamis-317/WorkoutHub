package com.WorkoutHub.workout_hub.service;


import com.WorkoutHub.workout_hub.dto.response.ExerciseLibraryResponse;
import com.WorkoutHub.workout_hub.mapper.ExerciseInfoMapper;
import com.WorkoutHub.workout_hub.repository.ExerciseInfoRepo;
import com.WorkoutHub.workout_hub.repository.MuscleRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkoutTrackingService {
    private final ExerciseInfoRepo exerciseInfoRepo;
    private final ExerciseInfoMapper exerciseInfoMapper;
    private final MuscleRepo muscleRepo;

    /**
     * Retrieves the complete exercise library containing all muscles and exercises with their muscle mappings.
     * @return {@link ExerciseLibraryResponse} containing lists of muscles and exercises
     */
    @Transactional(readOnly = true)
    public ExerciseLibraryResponse getExerciseLibrary(){
        var musclesEntity = muscleRepo.findAllByOrderByNameAsc();
        var exercisesEntity = exerciseInfoRepo.findAllWithMuscles();

        var musclesDto = exerciseInfoMapper.toMuscleDtoList(musclesEntity);
        var exercisesDto = exerciseInfoMapper.toExerciseInfoDtoList(exercisesEntity);

        return ExerciseLibraryResponse.builder()
                .muscles(musclesDto)
                .exercises(exercisesDto)
                .build();
    }


}
