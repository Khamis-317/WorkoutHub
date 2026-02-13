package com.WorkoutHub.workout_hub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;


import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Persistable<UUID> {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    protected UUID id = UUID.randomUUID();

    @Transient
    @JsonIgnore
    protected boolean isNew = true;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ", updatable = false)
    protected OffsetDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at")
    protected OffsetDateTime updatedAt;


    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew(){
        return isNew;
    }

    @PostLoad
    @PostPersist
    void markNotNew(){
        this.isNew = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
