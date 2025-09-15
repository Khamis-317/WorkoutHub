package com.WorkoutHub.workout_hub.entity;

import com.WorkoutHub.workout_hub.enums.Visibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "routines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "visibility", nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "gym_rat_id", nullable = false)
    private GymRat gymRat;

    @OneToMany(mappedBy = "routine", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoutineExercise> routineExercises;

    public void addRoutineExercise(RoutineExercise routineExercise) {
        if (routineExercises == null) {
            routineExercises = new ArrayList<>();
        }
        routineExercise.setRoutine(this);
        routineExercises.add(routineExercise);
    }
}