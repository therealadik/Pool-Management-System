package com.example.pool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NonWorkingHoursException extends RuntimeException{
    public NonWorkingHoursException(String message){
        super(message);
    }
}
