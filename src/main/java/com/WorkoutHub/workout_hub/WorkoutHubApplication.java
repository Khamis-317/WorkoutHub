package com.WorkoutHub.workout_hub;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Muscle;
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
			// createExercises(exRepo);
			addExerciseWithExistingMuscles(exRepo, mRepo);

			// TODO: test delete cascades (done)
			// TODO: Add unique constraint for muscle and exercise names (done)
			// TODO: test adding an exercise existing muscles (done)
			// TODO: test finding exercise only by id
			// TODO: test finding exercise only by name
			// TODO: test finding exercise with muscle group
			// TODO: test the nullable constraint
			// TODO: test the unique constraint
		};
	}

	private void createExercises(ExerciseRepo repo) {
		// Bench Press - Muscles linkage
		Exercise ex1 = Exercise.builder().name("Barbell Bench Press").instructions("Push the bar").build();

		Muscle m1 = Muscle.builder().name("Chest").build();
		Muscle m2 = Muscle.builder().name("Triceps").build();

		ex1.addExerciseMuscle(m1, true);
		ex1.addExerciseMuscle(m2, false);

		// Shoulder Press - Muscles linkage
		Exercise ex2 = Exercise.builder().name("Shoulder Press").instructions("Push the bar up").build();

		Muscle m3 = Muscle.builder().name("Shoulder").build();

		ex2.addExerciseMuscle(m3, true);

		repo.save(ex1);
		repo.save(ex2);
	}

	private void addExerciseWithExistingMuscles(ExerciseRepo exRepo, MuscleRepo mRepo) {
		String exerciseName = "Lateral Raises";
		Exercise ex = exRepo.findByName(exerciseName)
				.orElse(null);
		if (ex == null) {
			ex = Exercise.builder().name(exerciseName).instructions("Raise your shoulders to the side").build();

			String muscleName = "Shoulder";
			Muscle muscle =  mRepo.findMuscleByName(muscleName)
					.orElse(Muscle.builder().name(muscleName).build());

			ex.addExerciseMuscle(muscle, true);

			exRepo.merge(ex);
		}
	}
}
