package kea.dat3.entities;

import kea.dat3.dto.ScreeningRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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


    @OneToMany(mappedBy = "screening")
    private Set<Reservation> screenings = new HashSet<>();


    @ManyToOne()
    private Staff createdBy;

    @ManyToOne()
    private Movie movie;

    @ManyToOne()
    private Cinema cinema;

    @ManyToOne()
    private Hall hall;

    public Screening(ScreeningRequest body){
        this.showTime = body.getShowTime();
        this.duration = body.getDuration();
        this.movie = body.getMovie();
        this.cinema = body.getCinema();
        this.createdBy = body.getUsername();
        this.hall = body.getHall();
        this.seatReservationCounter = 0;
    }
}
