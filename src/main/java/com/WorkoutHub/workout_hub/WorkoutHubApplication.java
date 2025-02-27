package com.WorkoutHub.workout_hub;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Muscle;
import com.WorkoutHub.workout_hub.enums.MuscleImportance;
import com.WorkoutHub.workout_hub.repository.ExerciseRepo;
import com.WorkoutHub.workout_hub.repository.MuscleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class WorkoutHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutHubApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ExerciseRepo exRepo, MuscleRepo mRepo) {
		return runner -> {

		};
	}

	private void createExercises(ExerciseRepo repo) {
		// Bench Press - Muscles linkage
		Exercise ex1 = Exercise.builder().name("Barbell Bench Press").instructions("Push the bar").build();

		Muscle m1 = Muscle.builder().name("Chest").importance(MuscleImportance.PRIMARY).build();
		Muscle m2 = Muscle.builder().name("Triceps").importance(MuscleImportance.SECONDARY).build();

		ex1.addMuscle(m1);
		ex1.addMuscle(m2);

		// Shoulder Press - Muscles linkage
		Exercise ex2 = Exercise.builder().name("Shoulder Press").instructions("Push the bar up").build();

		Muscle m3 = Muscle.builder().name("Shoulder").importance(MuscleImportance.PRIMARY).build();

		ex2.addMuscle(m3);

		repo.save(ex1);
		repo.save(ex2);
	}

	private void addExerciseWithExistingMuscles(ExerciseRepo exRepo, MuscleRepo mRepo) {
		String exerciseName = "Lateral Raises";
		Exercise ex = exRepo.findExerciseByName(exerciseName)
				.orElse(null);
		if (ex == null) {
			ex = Exercise.builder().name(exerciseName).instructions("Raise your shoulders to the side").build();

			String muscleName = "Shoulder";
			Muscle muscle = mRepo.findById(5)
					.orElse(Muscle.builder().name(muscleName).importance(MuscleImportance.PRIMARY).build());

			// to force a merge in the second save
			// because persisting an existing muscle would give an error
			exRepo.save(ex);

			ex.addMuscle(muscle);
			exRepo.save(ex);
		}
	}
}
