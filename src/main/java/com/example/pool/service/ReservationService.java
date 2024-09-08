package com.example.pool.service;

import com.example.pool.exception.ClientNotFoundException;
import com.example.pool.exception.ClientMaxReservationsExceededException;
import com.example.pool.exception.MaxReservationsPerHourException;
import com.example.pool.exception.NonWorkingHoursException;
import com.example.pool.model.Client;
import com.example.pool.model.Reservation;
import com.example.pool.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



@Service
public class ReservationService {
    private static final LocalTime WORK_DAY_START = LocalTime.of(9, 0);
    private static final LocalTime WORK_DAY_END = LocalTime.of(20, 0);

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ClientRepository clientRepository;

    public Map<LocalTime, Long> getAvailableSlots(LocalDate date){
        LocalDateTime startOfWorkDay = date.atTime(WORK_DAY_START);
        LocalDateTime endOfWorkDay = date.atTime(WORK_DAY_END);

        List<Reservation> reservations = reservationRepository.findByReservationTimeBetween(startOfWorkDay, endOfWorkDay);
        Map<LocalTime, Long> availableSlots = new TreeMap<>();

        for (LocalTime time = WORK_DAY_START; time.isBefore(WORK_DAY_END); time = time.plusHours(Reservation.MAX_RESERVATIONS_PER_HOUR)) {
            LocalTime timeEffectively = time;
            long count = reservations.stream().filter(reservation -> reservation.getReservationTime().toLocalTime().equals(timeEffectively)).count();

            if (count < Reservation.MAX_RESERVATIONS_PER_HOUR) {
                availableSlots.put(time, Reservation.MAX_RESERVATIONS_PER_HOUR-count);  // Если записей меньше 10, добавляем слот как доступный
            }
        }

        return availableSlots;
    }

    public long createReservation(Long clientId, LocalDateTime dateTime){
        
        if (dateTime.toLocalTime().isBefore(WORK_DAY_START) || dateTime.toLocalTime().isAfter(WORK_DAY_END)){
            throw new NonWorkingHoursException("Cannot create a reservation outside of working hours: " + dateTime);
        }
        
        Client client = clientRepository.findById(clientId).
                orElseThrow(() -> new ClientNotFoundException("Client with ID: " + clientId + "not found"));

        int countReservationClient = reservationRepository.countByClientIdAndReservationTimeBetween(clientId,
                dateTime.withHour(WORK_DAY_START.getHour()),
                dateTime.withHour(WORK_DAY_END.getHour()));

        if (countReservationClient >= Client.MAX_RESERVATION_COUNT){
            throw new ClientMaxReservationsExceededException();
        }
        if (reservationRepository.countByReservationTime(dateTime).size() >= Reservation.MAX_RESERVATIONS_PER_HOUR){
            throw new MaxReservationsPerHourException(dateTime);
        }

        Reservation newReservation = new Reservation();
        newReservation.setClient(client);
        newReservation.setReservationTime(dateTime);

        reservationRepository.save(newReservation);

        return newReservation.getId();
    }
}
