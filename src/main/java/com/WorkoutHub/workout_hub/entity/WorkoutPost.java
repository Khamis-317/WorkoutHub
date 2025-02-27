package com.WorkoutHub.workout_hub.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "workout_posts")
@Getter
@Setter
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

    @Override
    public String toString() {
        return "WorkoutPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", caption='" + caption + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", gymRatID=" + gymRat.getId() +
                '}';
    }
}
