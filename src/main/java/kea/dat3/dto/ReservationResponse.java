package kea.dat3.dto;

import kea.dat3.entities.Customer;
import kea.dat3.entities.Reservation;
import kea.dat3.entities.Screening;
import kea.dat3.entities.Seat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ReservationResponse {

    private final long id;

    private final Seat seat;

    private final Screening screening;

    private final String customerEmail;

    private LocalDateTime created = null;
    private LocalDateTime updated = null;

    public ReservationResponse(Reservation reservation, Boolean isAdmin){
        this.id = reservation.getId();
        this.seat = reservation.getSeat();
        this.screening = reservation.getScreening();
        this.customerEmail = reservation.getCustomer().getEmail();
        if (isAdmin){
            this.created = reservation.getCreated();
            this.updated = reservation.getUpdated();
        }
    }

    public List<ReservationResponse> getResponsesFromEntities(List<Reservation> reservations, boolean isAdmin){
        return reservations.stream().map( reservation -> new ReservationResponse(reservation, isAdmin)).collect(Collectors.toList());
    }
}
