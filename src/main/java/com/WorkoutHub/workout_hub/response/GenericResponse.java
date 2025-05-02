package com.WorkoutHub.workout_hub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class GenericResponse<T>{
    T data;
    boolean success;
    String message;
    LocalDateTime timestamp;

    //success response
    public static <T> GenericResponse<T> success(T data, String message){
        return GenericResponse.<T>builder()
                .data(data)
                .success(true)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> GenericResponse<T> success(T data){
        return GenericResponse.<T>builder()
                .data(data)
                .success(true)
                .message("")
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> GenericResponse<T> success(String message){
        return GenericResponse.<T>builder()
                .success(true)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    //error response
    public static <T> GenericResponse<T> error(T data, String message){
        return GenericResponse.<T>builder()
                .data(data)
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
    public static <T> GenericResponse<T> error(String message){
        return GenericResponse.<T>builder()
                .success(false)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }


}
