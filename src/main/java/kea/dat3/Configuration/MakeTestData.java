package kea.dat3.Configuration;

import kea.dat3.entities.Customer;
import kea.dat3.entities.Person;
import kea.dat3.entities.Role;
import kea.dat3.entities.Staff;
import kea.dat3.repositories.CustomerRepository;
import kea.dat3.repositories.PersonRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;


@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {

    PersonRepository personRepository;
    CustomerRepository customerRepository;
    //StaffRepository staffRepository;

    public MakeTestData(PersonRepository personRepository, CustomerRepository customerRepository/*, StaffRepository staffRepository*/) {
        this.personRepository = personRepository;
        this.customerRepository = customerRepository;
        /*this.staffRepository = staffRepository;*/
    }

    public void makeUsers() {
        Customer cust1 = new Customer("user", "McUser", "12345678", "user@mail.dk", "test12");
        Customer cust2 = new Customer("userin", "McUser", "12345679", "userin@mail.dk", "test12");
        //Staff staff = new Staff("user-admin@mail.dk","user_admin","test12");
        customerRepository.save(cust1);
        customerRepository.save(cust2);
        //staffRepository.save(staff);
        System.out.println("CREATED " + personRepository.count() + " TEST PERSONS");
    }

    public void makeMovies(){



    }

    public void makeCinemaSetup(){}

    public void makeTestBookings(){}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        makeUsers();
    }
}