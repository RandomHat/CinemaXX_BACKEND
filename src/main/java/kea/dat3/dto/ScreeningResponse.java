package kea.dat3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.dat3.entities.Screening;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningResponse {

     long id;
     LocalDateTime created;
     int duration;
     int seatReservationCounter;

     MovieResponse movieResponse;
     StaffResponse staffResponse;
     CinemaResponse cinemaResponse;
     HallResponse hallResponse;

    public ScreeningResponse(Screening screening, MovieResponse movieResponse, StaffResponse staffResponse, CinemaResponse cinemaResponse, HallResponse hallResponse, boolean isAdmin) {
        this.id = screening.getId();
        this.duration = screening.getDuration();
        this.seatReservationCounter = screening.getSeatReservationCounter();

        this.movieResponse = movieResponse;
        this.cinemaResponse = cinemaResponse;
        this.hallResponse = hallResponse;

        if(isAdmin){
        this.created = screening.getCreated();
        this.staffResponse = staffResponse;
        }
    }
}
