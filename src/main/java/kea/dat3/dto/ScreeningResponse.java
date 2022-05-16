package kea.dat3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.dat3.entities.Movie;
import kea.dat3.entities.Screening;
import kea.dat3.entities.Staff;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
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
    public ScreeningResponse(Screening screening){
        this.id = screening.getId();
        this.created = screening.getCreated();
        this.duration = screening.getDuration();
        this.seatReservationCounter = screening.getSeatReservationCounter();
        this.movieResponse = new MovieResponse(screening.getMovie());
        this.staffResponse = new StaffResponse(screening.getCreatedBy());
        this.cinemaResponse = new CinemaResponse(screening.getCinema());
        this.hallResponse = new HallResponse(screening.getHall());
    }
    public static List<ScreeningResponse> getScreeningFromEntities(List<Screening> screenings) {
        return screenings.stream().map(screening -> new ScreeningResponse(screening)).collect(Collectors.toList());
    }
}
