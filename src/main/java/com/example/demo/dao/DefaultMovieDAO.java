package com.example.demo.dao;

import com.example.demo.controller.mapper.MovieActorRowMapper;
import com.example.demo.controller.payload.MoviesWithActors;
import com.example.demo.entity.Actor;
import com.example.demo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DefaultMovieDAO implements MovieDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DefaultMovieDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Movie> getALl() {
        return jdbcTemplate.query("select * from movie", new BeanPropertyRowMapper<>(Movie.class));
    }
    public void save(Movie movie) {
        jdbcTemplate.update("insert into movie (movie_name, year_of_production) values (?, ?)",
                movie.getMovie_name(), movie.getYear_of_production());
    }

    public Movie showOne(int movie_id){
        return jdbcTemplate.query("select * from movie where movie_id=?", new Object[]{movie_id},
                        new BeanPropertyRowMapper<>(Movie.class))
                .stream().findAny().orElse(null);
    }
    public void update(int movie_id, Movie movie) {
        jdbcTemplate.update("update movie set movie_name = ?, year_of_production = ? where movie_id = ?",
                movie.getMovie_name(),
                movie.getYear_of_production(),
                movie_id);
    }

    public void delete(int movie_id) {
        jdbcTemplate.update("delete from movie_actor where movie_id = ?", movie_id);
        jdbcTemplate.update("delete from movie where movie_id = ?", movie_id);
    }
    public List<Actor> findActorsByMovieId(int movie_id) {
        return jdbcTemplate.query("select a.* from actor a join movie_actor ma on a.actor_id = ma.actor_id where ma.movie_id=?", new Object[]{movie_id}, new BeanPropertyRowMapper<>(Actor.class));
    }

    public void addActorToMovie(int movie_id, int actor_id) {
        jdbcTemplate.update("insert into movie_actor (movie_id, actor_id) values (?, ?)", movie_id, actor_id);
    }
    public List<MoviesWithActors> getAllMoviesWithActors() {
        String sql = "select m.movie_id, m.movie_name, m.year_of_production, a.actor_id, a.actor_name, a.age " +
                "from movie_actor ma " +
                "join movie m ON ma.movie_id = m.movie_id " +
                "join actor a ON ma.actor_id = a.actor_id";
        return jdbcTemplate.query(sql, new MovieActorRowMapper());
    }
    public Optional<Movie> getMovieByName(String movie_name) {
        return jdbcTemplate.query("select * from movie where movie_name=?", new Object[]{movie_name},
                new BeanPropertyRowMapper<>(Movie.class)).stream().findAny();
    }

}
