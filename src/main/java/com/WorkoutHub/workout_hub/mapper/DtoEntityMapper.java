package com.WorkoutHub.workout_hub.mapper;

import com.WorkoutHub.workout_hub.dto.ExerciseDto;
import com.WorkoutHub.workout_hub.dto.WorkoutCreationDto;
import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.entity.*;

import java.util.List;

public class DtoEntityMapper {

    public static WorkoutDto createWorkoutDto(Workout workout, List<Exercise> exercises, boolean detailed) {

        List<ExerciseDto> exList = exercises.stream().map(
                (detailed)? DtoEntityMapper::createExerciseDtoWithSets :
                        DtoEntityMapper::createExerciseDto
        ).toList();

        return WorkoutDto.builder()
                .totalVolume(workout.getTotalVolume())
                .id(workout.getId())
                .createdAt(workout.getCreatedAt())
                .duration(workout.getWorkoutpost().getDuration())
                .exercises(exList)
                .numberOfSets(workout.getNumberOfSets())
                .workoutTitle(workout.getWorkoutpost().getTitle())
                .visibility(workout.getWorkoutpost().getVisibility())
                .build();
    }

    public static Workout createWorkoutEntity(WorkoutCreationDto workoutCreationDto,List<Exercise> exercises ,GymRat gymRat){
        WorkoutPost wp = WorkoutPost.builder()
                .title(workoutCreationDto.getTitle())
                .caption(workoutCreationDto.getCaption())
                .visibility(workoutCreationDto.getVisibility())
                .startTime(workoutCreationDto.getStartTime())
                .duration(workoutCreationDto.getDuration())
                .build();

        Workout workout = Workout.builder()
                .exercises(exercises)
                .numberOfSets(workoutCreationDto.getNumberOfSets())
                .totalVolume(workoutCreationDto.getTotalVolume())
                .workoutpost(wp)
                .gymRat(gymRat)
                .build();
        wp.setWorkout(workout);
        exercises.forEach(exercise -> exercise.setWorkout(workout));
        return workout;
    }

    public static ExerciseDto createExerciseDto(Exercise exercise) {
        return ExerciseDto.builder()
                .exerciseName(exercise.getExerciseInfo().getName())
                .setsNum(exercise.getSets().toArray().length)
                .exerciseInfoId(exercise.getExerciseInfo().getId())
                .build();
    }


    public static ExerciseDto createExerciseDtoWithSets(Exercise exercise) {
        return ExerciseDto.builder()
                .exerciseName(exercise.getExerciseInfo().getName())
                .sets(exercise.getSets())
                .exerciseInfoId(exercise.getExerciseInfo().getId())
                .build();
    }

    public static Exercise createExerciseEntity(ExerciseDto exerciseDto, ExerciseInfo exerciseInfo){
        Exercise ex = Exercise.builder()
                .exerciseInfo(exerciseInfo)
                .sets(exerciseDto.getSets())
                .build();
        exerciseDto.getSets().forEach(set -> set.setExercise(ex));
        return  ex;
    }
}
