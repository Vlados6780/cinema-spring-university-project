package com.example.demo.controller.mapper;

import com.example.demo.controller.payload.MoviesWithActors;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieActorRowMapper implements RowMapper<MoviesWithActors> {
    @Override
    public MoviesWithActors mapRow(ResultSet rs, int rowNum) throws SQLException {
        MoviesWithActors movieActor = new MoviesWithActors();
        movieActor.setMovieId(rs.getInt("movie_id"));
        movieActor.setMovieName(rs.getString("movie_name"));
        movieActor.setYearOfProduction(rs.getInt("year_of_production"));
        movieActor.setActorId(rs.getInt("actor_id"));
        movieActor.setActorName(rs.getString("actor_name"));
        movieActor.setActorAge(rs.getInt("age"));
        return movieActor;
    }
}
