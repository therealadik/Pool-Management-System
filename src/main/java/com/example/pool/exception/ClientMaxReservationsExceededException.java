package com.example.pool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientMaxReservationsExceededException extends RuntimeException {

    public ClientMaxReservationsExceededException() {
        super("Client has exceeded the maximum number of reservations.");
    }
}
