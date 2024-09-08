package com.example.pool.controller;

import com.example.pool.dto.CancelRequest;
import com.example.pool.dto.ReservationRequest;
import com.example.pool.model.Reservation;
import com.example.pool.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0/pool/timetable")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Operation(summary = "Get all occupied slots")
    @GetMapping("/all")
    public ResponseEntity<Map<LocalTime, Long>> getOccupiedSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        var occupiedSlots = reservationService.getOccupiedSlots(date);
        return ResponseEntity.ok(occupiedSlots);
    }

    @Operation(summary = "Get all available slots")
    @GetMapping("/available")
    public ResponseEntity<Map<LocalTime, Long>> getAvailableSlots(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        var availableSlots=  reservationService.getAvailableSlots(date);

        return ResponseEntity.ok(availableSlots);
    }

    @Operation(summary = "Get slots by clientName")
    @GetMapping("/search/clientName")
    public ResponseEntity<List<Reservation>> getReservationsByClientName(@RequestParam String name){
        List<Reservation> reservations = reservationService.getReservationsByClientName(name);

        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "Get slots by date")
    @GetMapping("/search/date")
    public ResponseEntity<List<Reservation>> getReservationsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<Reservation> reservations = reservationService.getReservationsByDate(date);

        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "cancel slot by id")
    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelReservation(@RequestBody CancelRequest request){
        reservationService.cancelReservation(request.getOrderId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "reserve slot")
    @PostMapping("/reserve")
    public ResponseEntity<Long> reserve(@RequestBody ReservationRequest request) {

        long reserveId = reservationService.createReservation(request.getClientId(), request.getDatetime());
        return ResponseEntity.ok(reserveId);
    }
}