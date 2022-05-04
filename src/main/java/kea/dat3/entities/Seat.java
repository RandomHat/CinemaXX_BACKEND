package kea.dat3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Seat {

    @Id
    int id;

    int row;
    int seatNo;

    @ManyToOne
    Hall hallId;

    public Seat(int id, int row, int seatNo) {
        this.id = id;
        this.row = row;
        this.seatNo = seatNo;
    }
}
