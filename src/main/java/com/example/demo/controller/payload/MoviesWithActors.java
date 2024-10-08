package com.example.demo.controller.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviesWithActors {
    private int movieId;
    private String movieName;
    private int yearOfProduction;
    private int actorId;
    private String actorName;
    private int actorAge;
}
