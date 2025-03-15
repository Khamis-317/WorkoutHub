package com.WorkoutHub.workout_hub.entity;


import com.WorkoutHub.workout_hub.enums.Visibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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


    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "visibility", nullable = false)
    Visibility visibility;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    //region Relations with other entities
    @ManyToOne(
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "gym_rat_id", nullable = false)
    private GymRat gymRat;
    //endregion

}
