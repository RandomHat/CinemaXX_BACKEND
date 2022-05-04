package kea.dat3.entities;

import kea.dat3.security.UserWithPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Staff extends Person {

    @Column(nullable = false, name = "worker_id" )
    private String workerId;

    @OneToMany(mappedBy = "createdBy")
    Set<Screening> screeningsCreated = new HashSet<>();

    public Staff(String email, String username, String password, String workerId){
        super(email,username,password);
        super.addRole(Role.ADMIN);
        this.workerId = workerId;
    }


}
