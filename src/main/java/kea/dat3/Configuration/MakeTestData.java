package kea.dat3.Configuration;

import com.fasterxml.jackson.databind.JsonNode;
import kea.dat3.entities.*;
import kea.dat3.repositories.*;
import kea.dat3.utils.Fetcher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    PersonRepository personRepository;
    CustomerRepository customerRepository;
    StaffRepository staffRepository;
    MovieRepository movieRepository;
    CinemaRepository cinemaRepository;

    public MakeTestData(PersonRepository personRepository, CustomerRepository customerRepository,
                        StaffRepository staffRepository, CinemaRepository cinemaRepository,
                        MovieRepository movieRepository) {

        this.personRepository = personRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public void makeUsers() {
        Customer cust1 = new Customer("user", "McUser", "12345678", "user@mail.dk", "test12");
        Customer cust2 = new Customer("userin", "McUser", "12345679", "userin@mail.dk", "test12");
        Staff staff = new Staff("user-admin@mail.dk","user_admin","test12","testworker1");
        customerRepository.save(cust1);
        customerRepository.save(cust2);
        staffRepository.save(staff);
    }

    public void makeMovies(){
        Fetcher fetcher;
        Random random = new Random();
        Map<String, Object> movieMap;
        Movie movie;
        Movie[] movies = new Movie[10];
        int backupCount = 0;

        for (int i = 0; i < movies.length; i++){
            fetcher = new Fetcher("https://api.themoviedb.org/3/movie/" + random.nextInt(1000));
            try {
                fetcher.fetch();
                movieMap = fetcher.getFetchedMap();
                JsonNode node = fetcher.asJsonNode();
                StringBuilder genres = new StringBuilder();
                node.get("genres").forEach((genre) -> genres.append(genre.get("name").asText().concat(" ")));
                movie = new Movie(
                        (String) movieMap.get("title"),
                        genres.toString(),
                        15,
                        (String) movieMap.get("poster_path"),
                        (String) movieMap.get("overview"),
                        LocalDate.parse( (String) movieMap.get("release_date")),
                        (double) movieMap.get("vote_average"),
                        (int) movieMap.get("runtime")
                );
            } catch(Exception e){
                e.printStackTrace();
                movie = null;
                i--; //Movie not found, rollback i counter to make sure we get 10 movies in total.
                backupCount++;
            }
            if (movie != null){
                movies[i] = movie;
            }
            if (backupCount >= 20){
                break;
            }
        }
        movieRepository.saveAll(Arrays.stream(movies).collect(Collectors.toList()));
    }

    private Set<Seat> makeHallSeats(Hall hall){
        Set<Seat> seats = new HashSet<>();
        for (int i = 1; i < 11; i++){
            for (int j = 1; j<31; j++){
                Seat seat = new Seat(i,j);
                seat.setHall(hall);
                seats.add(seat);
            }
        }

        return seats;
    }

    public void makeCinemaSetup(){
        Cinema cinema = new Cinema("Empire Bio", "Guldbergsgade 29F, 2200 København", "35 36 00 36");

        Hall hall1 = new Hall(1);
        hall1.setCinema(cinema);
        hall1.getSeats().addAll(makeHallSeats(hall1));
        Hall hall2 = new Hall(2);
        hall2.setCinema(cinema);
        hall2.getSeats().addAll(makeHallSeats(hall2));

        cinema.getHalls().add(hall1);
        cinema.getHalls().add(hall2);

        cinemaRepository.save(cinema); // should save all the objects due to cascading.
    }

    public void makeTestBookings(){}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        makeUsers();
        makeMovies();
        makeCinemaSetup();
    }
}
