package com.example.pool.controller;

import com.example.pool.model.Reservation;
import com.example.pool.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/pool/timetable")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/available")
    public ResponseEntity<Map<LocalTime, Long>> getAvailableSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        var availableSlots=  reservationService.getAvailableSlots(date);

        return ResponseEntity.ok(availableSlots);
    }

    @PostMapping("/reserve")
    public ResponseEntity<Long> reserve(
            @RequestParam Long clientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dateTime) {

        long reserveId = reservationService.createReservation(clientId, dateTime);
        return ResponseEntity.ok(reserveId);
    }


}