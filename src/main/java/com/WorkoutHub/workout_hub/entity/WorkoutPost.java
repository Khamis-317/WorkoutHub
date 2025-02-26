package com.WorkoutHub.workout_hub.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "workout_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WorkoutPost extends Workout{

    @Column(name = "caption")
    String caption;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "finish_time")
    LocalDateTime finishTime;
}
