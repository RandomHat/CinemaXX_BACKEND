package kea.dat3.entities;


import kea.dat3.dto.CustomerRequest;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Entity
public class Customer extends Person{

    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private Set<Reservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
    @EqualsAndHashCode.Exclude
    private Set<Ticket> tickets = new HashSet<>();

    public Customer(String firstName, String lastName, String phoneNumber, String email, String password){
        super(email, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addRole(Role.USER);
    }

    public Customer(CustomerRequest body) {
        super(body.getEmail(), body.getUsername(), body.getPassword());
        this.firstName = body.getFirstName();
        this.lastName = body.getLastName();
        this.phoneNumber = body.getPhoneNumber();
        this.addRole(Role.USER);
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
        reservation.setCustomer(this);
    }

    public void removeReservation(Reservation reservation){
        reservations.remove(reservation);
        reservation.setCustomer(null);
    }

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;
}
