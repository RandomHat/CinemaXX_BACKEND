package kea.dat3.services;

import kea.dat3.dto.ReservationResponse;
import kea.dat3.entities.Customer;
import kea.dat3.entities.Person;
import kea.dat3.repositories.CustomerRepository;
import kea.dat3.repositories.PersonRepository;
import kea.dat3.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PersonRepository personRepository;

    public ReservationService(ReservationRepository reservationRepository, PersonRepository personRepository){
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
    }

    public List<ReservationResponse> getUserReservations(Principal principal) {
        Person person = personRepository.findById(principal.getName()).get();
        return ReservationResponse.getResponsesFromEntities(reservationRepository.findAllByCustomer_Username(person.getUsername()),person.isAdmin());
    }
}
