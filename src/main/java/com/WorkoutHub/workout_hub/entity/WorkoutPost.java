package com.WorkoutHub.workout_hub.entity;


import com.WorkoutHub.workout_hub.enums.Visibility;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;

@Entity
@Table(name = "workout_posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutPost{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "caption")
    String caption;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "finish_time")
    LocalDateTime finishTime;

    @Column(name = "visibility", nullable = false)
    Visibility visibility;

    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_id", nullable = false, unique = true)
    Workout workout;

}
