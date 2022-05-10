package kea.dat3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kea.dat3.entities.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {

    long id;
    String title;
    String genre;
    int ageLimit;
    String cover;
    String overview;
    LocalDate releaseDate;
    double rating;
    int duration;

    public MovieResponse(Movie movie) {
        this.title = movie.getTitle();
        this.genre = movie.getGenre();
        this.ageLimit = movie.getAgeLimit();
        this.cover = movie.getCover();
        this.overview = movie.getOverview();
        this.rating = movie.getRating();
        this.duration = movie.getDuration();
        this.id = movie.getId();

    }

    public static List<MovieResponse> getMoviesFromEntities(List<Movie> movies) {
        return movies.stream().map(movie -> new MovieResponse(movie)).collect(Collectors.toList());
    }
}
