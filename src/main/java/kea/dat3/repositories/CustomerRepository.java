package kea.dat3.repositories;

import kea.dat3.entities.Customer;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>, UtilityUserMethods<Customer> {
}
