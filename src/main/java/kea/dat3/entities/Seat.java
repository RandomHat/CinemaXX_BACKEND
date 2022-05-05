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

    @Id
    int id;

    int rowNo;
    int seatNo;

    @ManyToOne
    Hall hallId;

    @OneToMany(mappedBy = "seat", fetch = FetchType.EAGER)
    private Set<Reservation> reservations = new HashSet<>();

    public Seat(int id, int rowNo, int seatNo) {
        this.id = id;
        this.rowNo = rowNo;
        this.seatNo = seatNo;
    }
}
