package com.WorkoutHub.workout_hub.service;

import com.WorkoutHub.workout_hub.dto.WorkoutDto;
import com.WorkoutHub.workout_hub.dto.WorkoutSimpleDto;
import com.WorkoutHub.workout_hub.entity.Workout;
import com.WorkoutHub.workout_hub.repository.GymRatRepo;
import com.WorkoutHub.workout_hub.repository.WorkoutRepo;
import com.WorkoutHub.workout_hub.response.PageResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    WorkoutRepo workoutRepo;
    GymRatRepo gymRatRepo;
    WorkoutServiceImpl(WorkoutRepo workoutRepo, GymRatRepo gymRatRepo) {
        this.workoutRepo = workoutRepo;
        this.gymRatRepo = gymRatRepo;
    }

    @Override
    public PageResponse<WorkoutSimpleDto> getAllWorkouts(int userId, int pageNumber, int pageSize) {
        validateUserExistence(userId);

        Page<Workout> page = workoutRepo.findByGymRatId(userId, PageRequest.of(pageNumber, pageSize));
        Page<WorkoutSimpleDto> workouts = page.map(WorkoutSimpleDto::createDto);
        return new PageResponse<>(workouts);
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


    private void  validateUserExistence(int userId){
        if (!gymRatRepo.existsById(userId)){
            throw new EntityNotFoundException("Entity Not Found: Gymrat with id: " + userId + " is not found.");
        }
    }
}
