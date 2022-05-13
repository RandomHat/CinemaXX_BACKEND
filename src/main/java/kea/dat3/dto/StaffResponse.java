package kea.dat3.dto;

import kea.dat3.entities.Staff;
import lombok.Getter;

@Getter
public class StaffResponse {

    String workerId;
    String email;
    String username;

    public StaffResponse(Staff body) {
        this.username = body.getUsername();
        this.workerId = body.getWorkerId();
        this.email = body.getEmail();
    }
}
