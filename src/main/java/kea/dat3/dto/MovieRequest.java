package kea.dat3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    private String title;
    private String genre;
    private int ageLimit;
    private String cover;
    private String overview;
    private LocalDate releaseDate;
    private double rating;
    private int duration;

}
