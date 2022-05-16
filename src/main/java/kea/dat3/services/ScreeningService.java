package kea.dat3.services;

import kea.dat3.dto.*;
import kea.dat3.entities.*;
import kea.dat3.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScreeningService {

    ScreeningRepository screeningRepository;
    MovieRepository movieRepository;
    HallRepository hallRepository;
    CinemaRepository cinemaRepository;
    StaffRepository staffRepository;


    public ScreeningService(ScreeningRepository screeningRepository, MovieRepository movieRepository, HallRepository hallRepository, CinemaRepository cinemaRepository, StaffRepository staffRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.hallRepository = hallRepository;
        this.cinemaRepository = cinemaRepository;
        this.staffRepository = staffRepository;
    }

    public ScreeningResponse getScreening(Long id, boolean isAdmin){
        return createResponse(id,isAdmin);
    }

    public ScreeningResponse addScreening(ScreeningRequest body) {
        Screening screeningToBeSaved = createScreeningBody(body);
        Screening screeningNew = screeningRepository.save(screeningToBeSaved);
        return createResponse(screeningNew.getId(),true);}

    private ScreeningResponse createResponse(Long id, boolean isAdmin){
        //Error msg handling?
        Screening screening = screeningRepository.findById(id).orElseThrow();
        CinemaResponse cinemaResponse = new CinemaResponse(screening.getCinema());
        HallResponse hallResponse = new HallResponse(screening.getHall());
        MovieResponse movieResponse = new MovieResponse(screening.getMovie());
        StaffResponse staffResponse = new StaffResponse(screening.getCreatedBy());
        return new ScreeningResponse(screening,movieResponse,staffResponse,cinemaResponse,hallResponse,isAdmin);
    }

    private Screening createScreeningBody(ScreeningRequest body){
        int duration = body.getDuration();
        LocalDateTime showtime = body.getShowTime();
        Movie movie = movieRepository.getById(body.getMovieId());
        Cinema cinema = cinemaRepository.getById(body.getCinemaId());
        Hall hall = hallRepository.getById(body.getHallId());
        Staff staff = staffRepository.getById(body.getUsername());

        return new Screening(duration,showtime,movie,cinema,hall,staff);
    }

    public List<ScreeningResponse> getScreenings() {
        return ScreeningResponse.getScreeningFromEntities(screeningRepository.findAll());
    }

}
