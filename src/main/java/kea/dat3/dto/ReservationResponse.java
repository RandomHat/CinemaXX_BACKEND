package kea.dat3.dto;

import kea.dat3.entities.Reservation;
import kea.dat3.entities.Screening;
import kea.dat3.entities.Seat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ReservationResponse {

    private long id;

    //private Seat seat; TODO Implement seat.

    private String movieTitle;

    private LocalDateTime showTime;

    private String customerEmail;

    private LocalDateTime created;
    private LocalDateTime updated;

    public ReservationResponse(Reservation reservation, Boolean isAdmin){
        this.id = reservation.getId();
        //this.seat = reservation.getSeat();
        this.movieTitle = reservation.getScreening().getMovie().getTitle();
        this.showTime = reservation.getScreening().getShowTime();
        this.customerEmail = reservation.getCustomer().getEmail();
        if (isAdmin){
            this.created = reservation.getCreated();
            this.updated = reservation.getUpdated();
        }
    }

    public static List<ReservationResponse> getResponsesFromEntities(List<Reservation> reservations, boolean isAdmin){
        return reservations.stream().map( reservation -> new ReservationResponse(reservation, isAdmin)).collect(Collectors.toList());
    }
}
