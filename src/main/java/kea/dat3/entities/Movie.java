package kea.dat3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private long id;

    private String title;
    private String Genre;
    private int ageLimit;
    private String cover;

    @Lob
    private String overview;
    private LocalDate releaseDate;
    private double Rating;
    private int duration;

    @OneToMany(mappedBy = "movie")
    Set<Screening> screenings = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Cinema> cinemas = new HashSet<>();

    public Movie(String title, String genre, int ageLimit, String cover, String overview, LocalDate releaseDate, double rating, int duration) {
        this.title = title;
        Genre = genre;
        this.ageLimit = ageLimit;
        this.cover = cover;
        this.overview = overview;
        this.releaseDate = releaseDate;
        Rating = rating;
        this.duration = duration;
    }
}
