package com.example.demo.util;

import com.example.demo.dao.MovieDAO;
import com.example.demo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MovieValidator implements Validator {
    private final MovieDAO movieDAO;

    @Autowired
    public MovieValidator(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }


    public boolean supports(Class<?> aClass) {
        return Movie.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Movie movie = (Movie) o;

        if (!Character.isUpperCase(movie.getMovie_name().codePointAt(0))) {
            errors.rejectValue("movie_name", "",
                    "The name of movie must begin with a capital letter.");
        }
        if (movie.getYear_of_production() <= 1901) {
            errors.rejectValue("year_of_production", "", "Year of production must be greater than 1900.");
        }
    }
    public void validateNewMovie(Object o, Errors errors){
        Movie movie = (Movie) o;

        if (movieDAO.getMovieByName(movie.getMovie_name()).isPresent()) {
            errors.rejectValue("movie_name", "", "Movie with that name already exists");
        }
    }
}
