package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.ExerciseTemplateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ExerciseTemplateController {
    @PostMapping("/exercise-template")
    ResponseEntity<?> createExerciseTemplate(ExerciseTemplateDto exercise) {

        return null;
    }

}
