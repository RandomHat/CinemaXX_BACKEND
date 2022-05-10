package kea.dat3.repositories;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import kea.dat3.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @BeforeAll
    static void setup(@Autowired CustomerRepository customerRepository){
        Customer cust1 = new Customer("user", "McUser", "12345678", "user@mail.dk", "test12");
        Customer cust2 = new Customer("userin", "McUser", "12345679", "userin@mail.dk", "test12");
        customerRepository.save(cust1);
        customerRepository.save(cust2);
    }

    @Test
    void existsByEmailTest(){
        // Arrange
        String correctEmail = "user@mail.dk";
        String wrongEmail = "derp@herp.org";


        // Act
        boolean correctResult = customerRepository.existsByEmail(correctEmail);
        boolean failedResult = customerRepository.existsByEmail(wrongEmail);

        // Assert
        assertTrue(correctResult);
        assertFalse(failedResult);
    }

    /*

    @Test
    void
*/

    // TODO: repository tests with h2 database

}
