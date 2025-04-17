package com.WorkoutHub.workout_hub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class GenericResponse<T>{
    T data;
    boolean success;
    String message;

    //success response
    public static <T> GenericResponse<T> success(T data, String message){
        return GenericResponse.<T>builder()
                .data(data)
                .success(true)
                .message(message)
                .build();
    }

    public static <T> GenericResponse<T> success(T data){
        return GenericResponse.<T>builder()
                .data(data)
                .success(true)
                .message("")
                .build();
    }

    //error response
    public static <T> GenericResponse<T> error(String message){
        return GenericResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }


}
