package com.example.pool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxReservationsPerHourException extends RuntimeException {
    public MaxReservationsPerHourException(LocalDateTime reservationTime){
        super("The maximum number of reservations for the time " + reservationTime.toString() + " has been reached.");
    }
}
