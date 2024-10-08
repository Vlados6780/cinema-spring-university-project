package com.example.demo.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private int movie_id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 200, message = "Name cannot be longer than 200 characters")
    private String movie_name;

    @Min(value = 1901, message = "Year of production must be greater than 1900")
    private int year_of_production;
}
