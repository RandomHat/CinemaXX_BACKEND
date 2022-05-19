package kea.dat3.services;

import static org.junit.jupiter.api.Assertions.*;

import kea.dat3.dto.ReservationResponse;
import kea.dat3.entities.*;
import kea.dat3.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    ReservationRepository reservationRepository;

    @Mock
    Principal principal;


    static Customer customer;
    static Movie movie;
    static Screening screening;
    static Reservation reservation;



    @BeforeAll
    static void setup(
            @Autowired CustomerRepository customerRepository,
            @Autowired MovieRepository movieRepository,
            @Autowired ScreeningRepository screeningRepository,
            @Autowired ReservationRepository reservationRepository
    ){
      
        reservationRepository.deleteAll();
        screeningRepository.deleteAll();
        customerRepository.deleteAll();
        movieRepository.deleteAll();

        customer = customerRepository.save(new Customer("test", "mctest", "12345678","test@mail.dk", "test12"));
        movie = movieRepository.save(new Movie("title", "genre", 15, "cover", "overview", LocalDate.of(2022, 5, 16), 10L, 90 ));
        screening = screeningRepository.save(new Screening(90, LocalDateTime.of(2022, 6, 20, 19,30), movie, null, null, null));
        reservation = reservationRepository.save(new Reservation(null, screening, customer));
        screening.addReservation(reservation);
        customer.addReservation(reservation);
    }
    @Test
    public void reservationsLoadsByCurrentUser(){

        // Arrange
        Mockito.when(principal.getName()).thenReturn("test@mail.dk");

        // Act
        List<ReservationResponse> reservations = reservationService.getUserReservations(principal);

        // Assert
        assertEquals("test@mail.dk", reservations.get(0).getCustomerEmail());
    }
}
