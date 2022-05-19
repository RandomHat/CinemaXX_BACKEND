package kea.dat3.entities;

import kea.dat3.dto.MovieRequest;
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
    private long id;

    private String title;
    private String genre;
    private int ageLimit;
    private String cover;

    @Lob
    private String overview;
    private LocalDate releaseDate;
    private double rating;
    private int duration;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    Set<Screening> screenings = new HashSet<>();

    // Fixes a data integrity violation when removing Movies.
    @PreRemove
    private void preRemove() {
        screenings.forEach( screening -> screening.setMovie(null));
    }

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private Set<Cinema> cinemas = new HashSet<>();

    public void addScreening(Screening screening){
        screenings.add(screening);
        screening.setMovie(this);
    }

    public void removeScreening(Screening screening){
        screenings.remove(screening);
        screening.setMovie(null);
    }
    public Movie(String title, String genre, int ageLimit, String cover, String overview, LocalDate releaseDate, double rating, int duration) {
        this.title = title;
        this.genre = genre;
        this.ageLimit = ageLimit;
        this.cover = cover;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.duration = duration;
    }

    public Movie(MovieRequest body) {
    }
}
