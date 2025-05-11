package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.ExerciseTemplateDto;
import com.WorkoutHub.workout_hub.entity.Muscle;

import java.util.List;

interface ExerciseTemplateService {
    void createTemplate(ExerciseTemplateDto template);
    List<ExerciseTemplateDto> getAllExerciseTemplates();
    List<ExerciseTemplateDto> getExerciseTemplatesByMuscles(List<Muscle> muscles);
    ExerciseTemplateDto getExerciseTemplateById(int id);
    ExerciseTemplateDto updateExerciseTemplateById(int id);
    void deleteExerciseTemplateById(int id);
}
