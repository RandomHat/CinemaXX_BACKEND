package kea.dat3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String Genre;
    private int ageLimit;
    private String cover;
    private String overview;
    private LocalDate releaseDate;
    private double Rating;
    private int duration;

    @OneToMany(mappedBy = "movie")
    Set<Screening> screenings = new HashSet<>();

    @ManyToOne()
    private Cinema cinema;
}
