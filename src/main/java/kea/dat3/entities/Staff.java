package kea.dat3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Staff extends Person {

    @Column(nullable = false, unique = true, name = "worker_id" )
    private String workerId;

    @OneToMany(mappedBy = "createdBy")
    Set<Screening> screeningsCreated = new HashSet<>();

    public Staff(String email, String username, String password, String workerId){
        super(email,username,password);
        super.addRole(Role.ADMIN);
        this.workerId = workerId;
    }


}
