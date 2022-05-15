package kea.dat3.repositories;

import kea.dat3.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {

    List<Hall> findHallByCinema_Id(int cinemaId);
}
