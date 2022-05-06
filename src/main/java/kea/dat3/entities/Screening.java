package kea.dat3.entities;

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

    private int seat_Reservation_Counter;


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

    public Screening(int id, LocalDateTime created, int duration, int seat_Reservation_Counter) {
        this.id = id;
        this.created = created;
        this.duration = duration;
        this.seat_Reservation_Counter = seat_Reservation_Counter;
    }
}
