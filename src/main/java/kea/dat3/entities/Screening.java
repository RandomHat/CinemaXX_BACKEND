package kea.dat3.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
public class Screening {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    LocalDateTime created;

    int duration;

    int seat_Reservation_Counter;

    @OneToMany(mappedBy = "reservationId")
    private Set<Reservation> Screenings = new HashSet<>();

    @ManyToOne()
    private Staff createdBy;

    @ManyToOne()
    private Movie movie;

    @ManyToOne()
    private Cinema cinema;

    @ManyToOne()
    private Hall hall;
}
