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
public class Cinema {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    String address;
    String phoneNumber;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Hall> halls = new HashSet<>();

    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER)
    private Set<Screening> screenings = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Cinema_Movie",
            joinColumns = {@JoinColumn(name = "cinema")},
            inverseJoinColumns = {@JoinColumn(name = "movie")}
    )
    Set<Movie> movies = new HashSet<>();

    public Cinema(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
