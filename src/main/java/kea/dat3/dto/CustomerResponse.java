package kea.dat3.dto;

import kea.dat3.entities.Customer;
import kea.dat3.entities.Movie;
import kea.dat3.entities.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomerResponse extends PersonResponse {


    private String firstName;
    private String lastName;
    private String phoneNumber;

    public CustomerResponse(Customer customer) {
        super(customer);
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.phoneNumber = customer.getPhoneNumber();
    }
}
