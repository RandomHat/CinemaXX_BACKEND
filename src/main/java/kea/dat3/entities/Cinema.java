package kea.dat3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cinema {

    @Id
    int id;

    String name;
    String address;
    int phoneNumber;

    @OneToMany(mappedBy = "cinemaId", fetch = FetchType.EAGER)
    private Set<Hall> halls = new HashSet<>();

    public Cinema(int id, String name, String address, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
