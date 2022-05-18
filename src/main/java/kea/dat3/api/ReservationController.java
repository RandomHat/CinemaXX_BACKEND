package kea.dat3.api;

import kea.dat3.dto.ReservationResponse;
import kea.dat3.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    //test

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping()
    public List<ReservationResponse> getReservations(Principal principal){
       return reservationService.getUserReservations(principal);
    }

    @PostMapping("/create-reservations")
    public List<ReservationResponse> makeReservations(){
        return null; //TODO implement
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable long id){
    }
}
