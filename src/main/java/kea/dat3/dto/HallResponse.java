package kea.dat3.dto;

import kea.dat3.entities.Hall;
import lombok.Getter;

@Getter
public class HallResponse {

    int id;
    int hallNo;

    int numberOfSeats;

    public HallResponse(Hall body){
        this.id = body.getId();
        this.hallNo = body.getHallNo();
        this.numberOfSeats = body.numberOfSeats();
    }
}
