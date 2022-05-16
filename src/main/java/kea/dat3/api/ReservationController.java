package kea.dat3.api;

import kea.dat3.dto.ReservationResponse;
import kea.dat3.entities.Reservation;
import kea.dat3.services.ReservationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RolesAllowed({"USER, ADMIN"})
@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public List<ReservationResponse> getReservations(){

    }

    @PostMapping("/create-reservations")
    public List<ReservationResponse> makeReservations(){

    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable long id){

    }
}
