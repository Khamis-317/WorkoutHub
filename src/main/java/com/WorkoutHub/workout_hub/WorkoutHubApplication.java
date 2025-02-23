package com.WorkoutHub.workout_hub;

import com.WorkoutHub.workout_hub.entity.Exercise;
import com.WorkoutHub.workout_hub.entity.Muscle;
import com.WorkoutHub.workout_hub.repository.ExerciseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkoutHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutHubApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ExerciseRepo Repo) {
		return runner -> {
			createExercises(Repo);
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
}
