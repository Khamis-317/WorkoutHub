package com.WorkoutHub.workout_hub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T>{
    List<T> page;
    int pageNumber;
    int totalPages;
    long totalElements;
    boolean isLast;

    public PageResponse(Page<T> page) {
        this.page = page.getContent();
        this.pageNumber = page.getNumber();
        this.totalPages = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.isLast = page.isLast();
    }
}
