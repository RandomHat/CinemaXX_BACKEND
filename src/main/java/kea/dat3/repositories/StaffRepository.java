package kea.dat3.repositories;

import kea.dat3.entities.Person;
import kea.dat3.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository <Staff, String> {
}
