package kea.dat3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CustomerRequest extends PersonRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;


    public CustomerRequest(String username, String password, String email, String firstName, String lastName, String phoneNumber) {
        super(username, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
