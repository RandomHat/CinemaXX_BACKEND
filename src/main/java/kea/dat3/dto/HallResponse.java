package kea.dat3.dto;

import kea.dat3.entities.Hall;

public class HallResponse {

    int id;
    int hallNo;

    public HallResponse(Hall body){
        this.id = body.getId();
        this.hallNo = body.getHallNo();
    }
}
