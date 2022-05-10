package kea.dat3.services;

import kea.dat3.dto.*;
import kea.dat3.entities.Screening;
import kea.dat3.repositories.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService {

    ScreeningRepository screeningRepository;


    public ScreeningService(ScreeningRepository screeningRepository){
        this.screeningRepository = screeningRepository;
    }

    public ScreeningResponse getScreening(Long id, boolean isAdmin){
        return createResponse(id,isAdmin);
    }

    public ScreeningResponse addScreening(ScreeningRequest body, boolean isAdmin) {
        Screening screeningNew = screeningRepository.save(new Screening(body));
        return createResponse(screeningNew.getId(),isAdmin);}

    private ScreeningResponse createResponse(Long id, boolean isAdmin){
        //Error msg handling?
        Screening screening = screeningRepository.findById(id).orElseThrow();
        CinemaResponse cinemaResponse = new CinemaResponse(screening.getCinema());
        HallResponse hallResponse = new HallResponse(screening.getHall());
        MovieResponse movieResponse = new MovieResponse(screening.getMovie());
        StaffResponse staffResponse = new StaffResponse(screening.getCreatedBy());
        return new ScreeningResponse(screening,movieResponse,staffResponse,cinemaResponse,hallResponse,isAdmin);
    }
}
