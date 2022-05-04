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
public class Hall {

    @Id
    int id;

    int hallNo;

    @ManyToOne
    Cinema cinemaId;

    @OneToMany(mappedBy = "hallId", fetch = FetchType.EAGER)
    private Set<Seat> seats = new HashSet<>();


    public Hall(int id, int hallNo) {
        this.id = id;
        this.hallNo = hallNo;
    }
}