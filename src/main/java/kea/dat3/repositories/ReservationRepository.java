package kea.dat3.repositories;

import kea.dat3.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    public List<Reservation> findAllByCustomer_Username(String Customer_username);
}
