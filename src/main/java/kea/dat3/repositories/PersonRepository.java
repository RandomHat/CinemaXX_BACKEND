package kea.dat3.repositories;

import kea.dat3.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,String>, UtilityUserMethods<Person> {
}
