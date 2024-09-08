package com.example.pool.repository;

import com.example.pool.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByReservationTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    List<Reservation> findByClientName(String clientName);
    int countByClientIdAndReservationTimeBetween(Long clientId, LocalDateTime startTime, LocalDateTime endTime);
    List<Reservation> findByReservationTime(LocalDateTime reservationTime);
}
