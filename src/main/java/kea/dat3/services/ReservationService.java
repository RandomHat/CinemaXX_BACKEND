package kea.dat3.services;

import kea.dat3.dto.ReservationRequest;
import kea.dat3.dto.ReservationResponse;
import kea.dat3.entities.*;
import kea.dat3.error.Client4xxException;
import kea.dat3.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;

    private Customer getCustomerFromPrincipal(Principal principal){
        return customerRepository.findById(principal.getName()).get();
    }

    private Reservation findReservation(long id){
        return reservationRepository.findById(id).orElseThrow(() -> new Client4xxException("No car with provided ID found", HttpStatus.NOT_FOUND));
    }

    public List<ReservationResponse> getUserReservations(Principal principal) {
        Person person = getCustomerFromPrincipal(principal);
        return ReservationResponse.getResponsesFromEntities(reservationRepository.findAllByCustomer_Username(person.getUsername()),person.isAdmin());
    }

    public List<ReservationResponse> getAllReservations(){
        return ReservationResponse.getResponsesFromEntities(reservationRepository.findAll(), true);
    }

    public List<ReservationResponse> makeReservations(ReservationRequest request, Principal principal){
        List<Reservation> newReservations = new ArrayList<>();
        Screening screening = screeningRepository.getById(request.getScreeningId());
        Customer customer = getCustomerFromPrincipal(principal);

        for (int seatId : request.getSeatIdList()) {
            Seat seat = seatRepository.getById(seatId);
            newReservations.add(new Reservation(seat, screening, customer));
        }

        // Keep bidirectional Relathionships up to date, and save through customer.
        newReservations.forEach(customer::addReservation);
        customerRepository.save(customer);

        return ReservationResponse.getResponsesFromEntities(newReservations, false);
    }

    public void deleteReservation(long id){
        Reservation reservation = findReservation(id);
        Customer customer = reservation.getCustomer();
        customer.removeReservation(reservation);
        reservationRepository.deleteById(id);
    }

    public void deleteReservation(long id, Principal principal){
        Reservation reservation = findReservation(id);
        Customer customer = getCustomerFromPrincipal(principal);

        if (reservation.getCustomer().equals(customer)){
            deleteReservation(id);
        }
    }
}
