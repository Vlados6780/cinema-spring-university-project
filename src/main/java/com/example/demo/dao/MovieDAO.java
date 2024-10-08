package com.example.demo.dao;

import com.example.demo.controller.payload.MoviesWithActors;
import com.example.demo.entity.Actor;
import com.example.demo.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDAO {
    List<Movie> getALl();
    void save(Movie movie);
    Movie showOne(int movie_id);
    void update(int movie_id, Movie movie);
    void delete(int movie_id);
    List<Actor> findActorsByMovieId(int movie_id);
    void addActorToMovie(int movie_id, int actor_id);
    List<MoviesWithActors> getAllMoviesWithActors();
    Optional<Movie> getMovieByName(String movie_name);
}
