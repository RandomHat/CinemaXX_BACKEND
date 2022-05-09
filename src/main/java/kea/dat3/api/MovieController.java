package kea.dat3.api;

import kea.dat3.dto.MovieRequest;
import kea.dat3.dto.MovieResponse;
import kea.dat3.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService) {this.movieService = movieService;}

    @GetMapping
    public List<MovieResponse> getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public MovieResponse getMovie(@PathVariable long id) throws Exception {
        return movieService.getMovie(id);
    }


    @PostMapping
    public MovieResponse addMovie(@RequestBody MovieRequest body) {
        return movieService.addMovie(body);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
    }
}
