package com.WorkoutHub.workout_hub;


import com.WorkoutHub.workout_hub.entity.*;
import com.WorkoutHub.workout_hub.enums.Visibility;
import com.WorkoutHub.workout_hub.repository.*;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Muscle;
import com.WorkoutHub.workout_hub.enums.MuscleImportance;
import com.WorkoutHub.workout_hub.repository.ExerciseRepo;
import com.WorkoutHub.workout_hub.repository.MuscleRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class WorkoutHubApplication {

	private final WorkoutPostRepo workoutPostRepo;

	public WorkoutHubApplication(WorkoutPostRepo workoutPostRepo) {
		this.workoutPostRepo = workoutPostRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorkoutHubApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			ExerciseRepo exRepo,
			MuscleRepo mRepo,
			GymRatRepo gymRatRepo,
			WorkoutRepo workoutRepo,
			WorkoutPostRepo workoutPostRepo,

	) {
		return runner -> {

//			createGymRat(gymRatRepo);
//			createWorkoutPostAttachToUser(gymRatRepo);
//			createWorkoutRoutineAttachToUser(gymRatRepo);
			//fetchAllUserPosts(gymRatRepo);
			//fetchAllUserPosts(workoutPostRepo);
//			fetchAllUserRoutine(gymRatRepo);
//			fetchAllUserRoutine(workoutRoutineRepo);
			//updateExistingWorkout(workoutPostRepo);
	//		updateExistingRoutine(workoutRoutineRepo);
//			deleteWorkoutPost(gymRatRepo);
//			deleteWorkoutPost(workoutRepo);
			//deleteWorkoutRoutine(gymRatRepo);
			//deleteWorkoutRoutine(workoutRoutineRepo);
			//deleteUser(gymRatRepo);


		};
	}

//	//region testing workout entities
//	private void deleteUser(GymRatRepo gymRatRepo) {
//		GymRat gymRat = gymRatRepo.findById(2).orElse(null);
//		gymRatRepo.delete(gymRat);
//
//	}
//
//	private void deleteWorkoutRoutine(WorkoutRoutineRepo workoutRoutineRepo) {
//		WorkoutRoutine workoutRoutine = workoutRoutineRepo.findById(8).orElse(null);
//
//		workoutRoutineRepo.delete(workoutRoutine);
//	}
//
//	private void deleteWorkoutRoutine(GymRatRepo gymRatRepo) {
//		GymRat gymRat = gymRatRepo.fetchUserRoutinesById(2).orElse(null);
//		WorkoutRoutine workoutRoutine =  (WorkoutRoutine) gymRat.getWorkouts().getFirst();
//		System.out.println(workoutRoutine);
//		gymRat.removeWorkout(workoutRoutine);
//		gymRatRepo.save(gymRat);
//	}
//
//	private void deleteWorkoutPost(WorkoutRepo workoutRepo) {
//		WorkoutPost workoutPost = workoutPostRepo.findById(3).orElse(null);
//
//		workoutRepo.delete(workoutPost);
//
//	}
//
//	private void deleteWorkoutPost(GymRatRepo gymRatRepo) {
//		GymRat gymRat = gymRatRepo.fetchUserPostsById(2).orElse(null);
//		WorkoutPost workoutPost =  (WorkoutPost) gymRat.getWorkouts().getFirst();
//		System.out.println(workoutPost);
//		gymRat.removeWorkout(workoutPost);
//		gymRatRepo.save(gymRat);
//
//	}
//
//	private void updateExistingRoutine(GymRatRepo gymRatRepo) {
//		GymRat gymRat = gymRatRepo.fetchUserRoutinesById(2).orElse(null);
//		Workout workout = gymRat.getWorkouts().getFirst();
//		WorkoutRoutine workoutRoutine = (WorkoutRoutine) workout;
//		workoutRoutine.setTitle("ya tara eh");
//		workoutRoutine.setVisibility(Visibility.OnlyFriends);
//		gymRatRepo.save(gymRat);
//
//	}
//
//	private void updateExistingRoutine(WorkoutRoutineRepo workoutRoutineRepo) {
//		WorkoutRoutine workoutRoutine = workoutRoutineRepo.findById(5).orElse(null);
//		workoutRoutine.setVisibility(Visibility.Private);
//		workoutRoutine.setTitle("Leg");
//		workoutRoutineRepo.save(workoutRoutine);
//	}
//
//
//	private void updateExistingWorkout(WorkoutPostRepo workoutPostRepo){
//		WorkoutPost workoutPost = workoutPostRepo.findById(4).orElse(null);
//		workoutPost.setCaption("this the updated workout");
//		workoutPost.setTitle("pull");
//		workoutPostRepo.save(workoutPost);
//	}
//
//	private void fetchAllUserRoutine(WorkoutRoutineRepo workoutRoutineRepo) {
//		List<WorkoutRoutine> workoutRoutines = workoutRoutineRepo.findUserRoutinesById(2);
//		for (WorkoutRoutine workoutPost : workoutRoutines){
//			System.out.println(workoutPost);
//		}
//	}
//
//	private void fetchAllUserRoutine(GymRatRepo gymRatRepo) {
//		GymRat gymRat = gymRatRepo.fetchUserRoutinesById(2).orElse(null);
//		for (Workout workoutRoutine : gymRat.getWorkouts()){
//			System.out.println(workoutRoutine);
//		}
//	}
//
//	private void fetchAllUserPosts(WorkoutPostRepo workoutPostRepo) {
//		List<WorkoutPost> workoutPosts = workoutPostRepo.findUserPostsById(2);
//		for (WorkoutPost workoutPost : workoutPosts){
//			System.out.println(workoutPost.getTitle());
//			System.out.println(workoutPost);
//		}
//	}
//
//	private void fetchAllUserPosts(GymRatRepo gymRatRepo) {
//		GymRat gymRat = gymRatRepo.fetchUserPostsById(1).orElse(null);
//		for (Workout workoutPost : gymRat.getWorkouts()){
//			System.out.println(workoutPost);
//		}
//
//	}
//
//	private void createWorkoutRoutineAttachToUser(GymRatRepo gymRatRepo) {
//		WorkoutRoutine workoutRoutine = WorkoutRoutine.builder()
//				.title("Push2")
//				.visibility(Visibility.Public)
//				.build();
//
//		GymRat gymRat = gymRatRepo.fetchUserRoutinesById(2).orElse(null);
//		gymRat.addWorkout(workoutRoutine);
//		gymRatRepo.save(gymRat);
//	}
//
//
//	private void createWorkoutPostAttachToUser(GymRatRepo gymRatRepo) {
//		WorkoutPost workoutPost = WorkoutPost.builder()
//				.caption("This is the first workout")
//				.title("Push2")
//				.startTime(LocalDateTime.now())
//				.finishTime(LocalDateTime.now())
//				.build();
//		GymRat gymRat = gymRatRepo.fetchUserPostsById(2).orElse(null);
//		gymRat.addWorkout(workoutPost);
//		gymRatRepo.save(gymRat);
//
//	}
//	//endregion
//
//	private void createGymRat(GymRatRepo gymRatRepo) {
//		GymRat gymRat = GymRat.builder().
//				email("mohamedkhamis20045@gmail.com").
//				password("1234").
//				username("mohamedkhaims")
//				.build();
//		GymRatProfile gymRatProfile = GymRatProfile.builder()
//				.firstName("Mohamed")
//				.lastName("Khamis")
//				.birthDate(LocalDate.of(2004,4,23))
//				.country("Egypt")
//				.build();
//		gymRat.setInfo(gymRatProfile);
//
//		gymRatRepo.save(gymRat);
//	}
//
//	private void createExercises(ExerciseRepo repo) {
//		// Bench Press - Muscles linkage
//		Exercise ex1 = Exercise.builder().name("Barbell Bench Press").instructions("Push the bar").build();
//
//		Muscle m1 = Muscle.builder().name("Chest").importance(MuscleImportance.PRIMARY).build();
//		Muscle m2 = Muscle.builder().name("Triceps").importance(MuscleImportance.SECONDARY).build();
//
//		ex1.addMuscle(m1);
//		ex1.addMuscle(m2);
//
//		// Shoulder Press - Muscles linkage
//		Exercise ex2 = Exercise.builder().name("Shoulder Press").instructions("Push the bar up").build();
//
//		Muscle m3 = Muscle.builder().name("Shoulder").importance(MuscleImportance.PRIMARY).build();
//
//		ex2.addMuscle(m3);
//
//		repo.save(ex1);
//		repo.save(ex2);
//	}
//
//	private void addExerciseWithExistingMuscles(ExerciseRepo exRepo, MuscleRepo mRepo) {
//		String exerciseName = "Lateral Raises";
//		Exercise ex = exRepo.findExerciseByName(exerciseName)
//				.orElse(null);
//		if (ex == null) {
//			ex = Exercise.builder().name(exerciseName).instructions("Raise your shoulders to the side").build();
//
//			String muscleName = "Shoulder";
//			Muscle muscle = mRepo.findById(5)
//					.orElse(Muscle.builder().name(muscleName).importance(MuscleImportance.PRIMARY).build());
//
//			// to force a merge in the second save
//			// because persisting an existing muscle would give an error
//			exRepo.save(ex);
//
//			ex.addMuscle(muscle);
//			exRepo.save(ex);
//		}
//	}
}
