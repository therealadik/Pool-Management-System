package com.example.pool.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    public static final int MAX_RESERVATIONS_PER_HOUR = 10;
    public static final int RESERVATION_HOUR_DURATION = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @Column(nullable = false)
    private boolean holidaySchedule = false;

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public boolean isHolidaySchedule() {
        return holidaySchedule;
    }

    public void setHolidaySchedule(boolean holidaySchedule) {
        this.holidaySchedule = holidaySchedule;
    }
}
