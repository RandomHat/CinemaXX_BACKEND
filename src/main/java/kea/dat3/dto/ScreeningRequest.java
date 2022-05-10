package kea.dat3.dto;

import kea.dat3.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningRequest {

    private int duration;
    private LocalDateTime showTime;
    private Movie movie;
    private Staff username;
    private Cinema cinema;
    private Hall hall;

}
