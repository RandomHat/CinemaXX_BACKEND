package kea.dat3.dto;

import kea.dat3.entities.Staff;

public class StaffResponse {

    String workerId;
    String email;
    String username;

    public StaffResponse(Staff body) {
        this.workerId = body.getWorkerId();
        this.username = body.getUsername();
        this.email = body.getEmail();
    }
}
