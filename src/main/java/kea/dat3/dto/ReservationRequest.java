package kea.dat3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    //private int[] seatIdList; TODO implement reservations with seatID's
    private int noSeats;
    private long screeningId;
}
