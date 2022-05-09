package kea.dat3.dto;

import kea.dat3.entities.Cinema;

public class CinemaResponse {

    int id;

    String name;
    String address;
    String phoneNumber;
    public CinemaResponse(Cinema body) {

        this.id = body.getId();
        this.name = body.getName();
        this.address = body.getAddress();
        this.phoneNumber = body.getPhoneNumber();

    }
}
