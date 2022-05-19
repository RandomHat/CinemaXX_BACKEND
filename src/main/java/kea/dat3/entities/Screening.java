package kea.dat3.entities;

import kea.dat3.dto.ScreeningRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Screening {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private LocalDateTime created;

    private int duration;

    private LocalDateTime showTime;

    private int seatReservationCounter;


    @OneToMany(mappedBy = "screening", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Reservation> reservations = new HashSet<>();


    @ManyToOne()
    private Staff createdBy;

    @ManyToOne()
    private Movie movie;

    @ManyToOne()
    private Cinema cinema;

    @ManyToOne()
    private Hall hall;

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
        reservation.setScreening(this);
    }

    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
        reservation.setScreening(null);
    }

    public Screening(int duration, LocalDateTime showTime, Movie movie, Cinema cinema, Hall hall, Staff staff){
        this.duration = duration;
        this.showTime = showTime;
        this.movie = movie;
        this.cinema = cinema;
        this.createdBy = staff;
        this.hall = hall;
        this.seatReservationCounter = 0;
    }

    public void incrementSeatReservationCounter(){
        this.seatReservationCounter++;
    }
    public void decrementSeatReservationCounter(){
        this.seatReservationCounter--;
    }
}
