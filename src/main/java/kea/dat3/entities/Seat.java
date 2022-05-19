package kea.dat3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Seat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int rowNo;
    int seatNo;

    @ManyToOne(fetch = FetchType.LAZY)
    Hall hall;

    @OneToMany(mappedBy = "seat", fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Set<Reservation> reservations = new HashSet<>();

    /*
    Following is utility methods to manage the bidirectional relationship, since it falls to us
    to make sure that both sides of the relationship is in sync.
    https://medium.com/@rajibrath20/the-best-way-to-map-a-onetomany-relationship-with-jpa-and-hibernate-dbbf6dba00d3
     */

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
        reservation.setSeat(this);
    }

    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
        reservation.setSeat(null);
    }

    public Seat(int rowNo, int seatNo) {
        this.rowNo = rowNo;
        this.seatNo = seatNo;
    }
}
