package kea.dat3.api;

import kea.dat3.dto.MovieRequest;
import kea.dat3.dto.MovieResponse;
import kea.dat3.services.MovieService;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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
    public MovieResponse getMovieAdmin(@PathVariable long id) throws Exception {
        return movieService.getMovie(id);
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public MovieResponse addMovie(@RequestBody MovieRequest body) {
        return movieService.addMovie(body);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public void deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
    }
}
