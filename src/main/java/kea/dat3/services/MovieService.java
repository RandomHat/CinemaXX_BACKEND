package kea.dat3.services;

import kea.dat3.dto.MovieRequest;
import kea.dat3.dto.MovieResponse;
import kea.dat3.entities.Movie;
import kea.dat3.error.Client4xxException;
import kea.dat3.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse> getMovies() {
        List<Movie> movies = movieRepository.findAll();
        return MovieResponse.getMoviesFromEntities(movies);
    }

    public MovieResponse getMovie(long id) throws Exception {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new Client4xxException("No car with provided ID found", HttpStatus.NOT_FOUND));
        return new MovieResponse(movie);
    }

    public MovieResponse addMovie(MovieRequest body) {
        Movie newMovie = movieRepository.save(new Movie(body));
        return new MovieResponse(newMovie);
    }

    public void deleteMovie(long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new Client4xxException("No car with provided ID found", HttpStatus.NOT_FOUND));
        try {
            movieRepository.delete(movie);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
