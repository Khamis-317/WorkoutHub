package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.dto.WorkoutSimpleDto;
import com.WorkoutHub.workout_hub.entity.Workout;
import com.WorkoutHub.workout_hub.repository.WorkoutRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    WorkoutRepo workoutRepo;

    WorkoutServiceImpl(WorkoutRepo workoutRepo) {
        this.workoutRepo = workoutRepo;
    }

    @Override
    public List<WorkoutSimpleDto> getAllWorkouts(int userId) {
        return workoutRepo.findByGymRatId(userId, PageRequest.of(/* hardcoded should be passed as a parameter*/0, 10))
                .stream()
                .map(WorkoutSimpleDto::createDto)
                .collect(Collectors.toList());
    }

    @Override
    public WorkoutDto createWorkout(Workout workout, int userId) {
        return null;
    }

    @Override
    public WorkoutDto getWorkoutById(int userId) {
        return null;
    }

    @Override
    public WorkoutDto updateWorkout(Workout workout) {
        return null;
    }

    @Override
    public void deleteWorkoutById(int id) {

    }
}
