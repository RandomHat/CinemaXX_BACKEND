package kea.dat3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer extends Person{

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
    private Set<Ticket> tickets = new HashSet<>();

    public Customer(String firstName, String lastName, String phoneNumber, String email, String password){
        super(email, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addRole(Role.USER);
    }

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
