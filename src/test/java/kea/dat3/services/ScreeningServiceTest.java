package kea.dat3.services;

import kea.dat3.dto.ScreeningRequest;
import kea.dat3.dto.ScreeningResponse;
import kea.dat3.entities.*;
import kea.dat3.repositories.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ScreeningServiceTest {

    @Autowired
    ScreeningRepository screeningRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    HallRepository hallRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    ReservationRepository reservationRepository;
    ScreeningService screeningService;

    static Long movie1, movie2;
    static int hall1, hall2;
    static String staff1, staff2;
    static int cinema1, cinema2;

    static Long screening1;

    @BeforeAll
    static void beforeAllSetup(@Autowired ScreeningRepository screeningRepository, @Autowired MovieRepository movieRepository, @Autowired HallRepository hallRepository, @Autowired StaffRepository staffRepository, @Autowired CinemaRepository cinemaRepository, @Autowired ReservationRepository reservationRepository){
        reservationRepository.deleteAll();
        screeningRepository.deleteAll();
        movieRepository.deleteAll();
        hallRepository.deleteAll();
        staffRepository.deleteAll();
        cinemaRepository.deleteAll();

        movie1 = movieRepository.save(new Movie("testTitle1","testGenre1",1,"testCover1","overviewTest1",LocalDate.of(2021,1,3),11.1,181)).getId();
        movie2 = movieRepository.save(new Movie("testTitle2","testGenre2",20,"testCover2","overviewTest2",LocalDate.of(2022,2,5),22.2,162)).getId();

        hall1 = hallRepository.save(new Hall(1)).getId();
        hall2 = hallRepository.save(new Hall(2)).getId();

        staff1 = staffRepository.save(new Staff("test1@mail.dk","testStaff1","test1","worker001")).getUsername();
        staff2 = staffRepository.save(new Staff("test2@mail.dk","testStaff2","test2","worker002")).getUsername();

        cinema1 = cinemaRepository.save(new Cinema("testBio1","banegaarden 1","11112222")).getId();
        cinema2 = cinemaRepository.save(new Cinema("testBio2","testvej 2","22223333")).getId();

        Movie movieGet1 = movieRepository.getById(movie1);
        Hall hallGet1 = hallRepository.getById(hall1);
        Staff staffGet1 = staffRepository.getById(staff1);
        Cinema cinemaGet1 = cinemaRepository.getById(cinema1);
        Screening screening = new Screening(2, LocalDateTime.of(2022,5,11,10,30),movieGet1,cinemaGet1,hallGet1,staffGet1);
        screening1 = screeningRepository.save(screening).getId();
    }

    @BeforeEach
    void setup(){
        screeningService = new ScreeningService(screeningRepository,movieRepository,hallRepository,cinemaRepository,staffRepository);
    }

    @Test
    void testGetScreening() {
        //Arrange
        String staffWorkerId = staffRepository.getById(staff1).getWorkerId();
        String cinemaName = cinemaRepository.getById(cinema1).getName();
        Screening screeningGet = screeningRepository.getById(screening1);

        //Act
        ScreeningResponse screeningAdmin = screeningService.getScreening(screening1,true);
        ScreeningResponse screeningUser = screeningService.getScreening(screening1,false);

        //Assert
        // check ID
        assertEquals(screeningGet.getId(), screeningAdmin.getId());
        assertEquals(screeningGet.getId(), screeningUser.getId());
        //Check admin rights
        assertEquals(staffWorkerId,screeningAdmin.getStaffResponse().getWorkerId());
        assertNull(screeningUser.getStaffResponse());
        assertEquals(screeningGet.getCreated(),screeningAdmin.getCreated());
        assertNull(screeningUser.getCreated());

        //Control if Eager
        assertEquals(cinemaName,screeningAdmin.getCinemaResponse().getName());

    }

    @Test
    void testAddScreening() {
        //Arrange
        ScreeningRequest screeningRequest = new ScreeningRequest(2, LocalDateTime.of(2022,5,11,10,30),movie1,staff1,cinema1,hall1);

        int repoSizeBeforeAdd = screeningRepository.findAll().size();

        //Act

        ScreeningResponse screeningNew = screeningService.addScreening(screeningRequest);

        //Assert
        assertEquals(1,repoSizeBeforeAdd);
        assertEquals(2,screeningRepository.findAll().size());
        assertEquals(2,screeningNew.getDuration());

    }
}