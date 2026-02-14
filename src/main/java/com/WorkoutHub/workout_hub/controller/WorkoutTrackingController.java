package com.WorkoutHub.workout_hub.controller;

import com.WorkoutHub.workout_hub.dto.request.WorkoutUploadRequest;
import com.WorkoutHub.workout_hub.dto.response.ExerciseHistoryResponse;
import com.WorkoutHub.workout_hub.dto.response.ExerciseLibraryResponse;
import com.WorkoutHub.workout_hub.dto.response.WorkoutUploadResponse;
import com.WorkoutHub.workout_hub.response.GenericResponse;
import com.WorkoutHub.workout_hub.security.userdetails.GymRatDetails;
import com.WorkoutHub.workout_hub.service.WorkoutTrackingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WorkoutTrackingController {

    private final WorkoutTrackingService workoutTrackingService;

    /**
     * Retrieve the exercise library.
     * <p>Endpoint: GET /api/v1/exercises</p>
     *
     * @return ResponseEntity containing a {@link GenericResponse} wrapping an
     *         {@link ExerciseLibraryResponse} with HTTP status 200 (OK)
     */
    @GetMapping("exercises")
    public ResponseEntity<GenericResponse<ExerciseLibraryResponse>> getExerciseLibrary(){
        var exerciseLibrary = workoutTrackingService.getExerciseLibrary();
        return ResponseEntity.ok(GenericResponse.success(exerciseLibrary));
    }

    /**
     * Upload a completed workout from the mobile app.
     * Uses client-generated UUIDs for idempotency (prevents duplicates on retry).
     * Creates both Workout and WorkoutPost entities.
     * Returns workout ID, sync timestamp, and new exercise history for cache update.
     *
     * @param request Workout data with client-generated UUIDs
     * @param principal Authenticated user extracted from JWT token
     * @return Workout upload response with new exercise history
     */
    @PostMapping("workouts")
    public ResponseEntity<GenericResponse<WorkoutUploadResponse>> uploadWorkout(
            @Valid @RequestBody WorkoutUploadRequest request,
            @AuthenticationPrincipal GymRatDetails principal
    ) {
        var userId = principal.getGymRat().getId();
        var result = workoutTrackingService.saveWorkout(request, userId);
        var status = result.alreadySynced() ? HttpStatus.OK : HttpStatus.CREATED;
        return ResponseEntity
                .status(status)
                .body(GenericResponse.success(result.response()));
    }

    /**
     * Get exercise history for the authenticated user.
     * If 'since' parameter is omitted, returns full history (initial load/reinstall).
     * If 'since' is provided, returns only changes after that timestamp (delta sync).
     *
     * @param principal Authenticated user extracted from JWT token
     * @param since Optional timestamp for delta sync
     * @return Exercise history
     */
    @GetMapping("exercise-history")
    public ResponseEntity<GenericResponse<ExerciseHistoryResponse>> getExerciseHistory(
            @AuthenticationPrincipal GymRatDetails principal,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime since
    ) {
        var userId = principal.getGymRat().getId();
        var history = workoutTrackingService.getUserExerciseHistory(userId, since);
        return ResponseEntity.ok(GenericResponse.success(history));
    }

}
