package com.example.demo.dao;

import com.example.demo.entity.Actor;
import com.example.demo.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DefaultActorDAO implements ActorDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DefaultActorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Actor> getALl() {
        return jdbcTemplate.query("select * from actor", new BeanPropertyRowMapper<>(Actor.class));
    }
    public void save(Actor actor) {
        jdbcTemplate.update("insert into actor (actor_name, age) values (?, ?)",
                actor.getActor_name(), actor.getAge());
    }

    public Actor showOne(int actor_id){
        return jdbcTemplate.query("select * from actor where actor_id=?", new Object[]{actor_id},
                        new BeanPropertyRowMapper<>(Actor.class))
                .stream().findAny().orElse(null);
    }
    public void update(int actor_id, Actor actor) {
        jdbcTemplate.update("update actor set actor_name = ?, age = ? where actor_id = ?",
                actor.getActor_name(),
                actor.getAge(),
                actor_id);
    }

    public void delete(int actor_id) {
        jdbcTemplate.update("delete from movie_actor where actor_id = ?", actor_id);
        jdbcTemplate.update("delete from actor where actor_id = ?", actor_id);
    }
    public List<Movie> findMoviesByActorId(int actor_id) {
        return jdbcTemplate.query("select m.* from movie m join movie_actor ma on m.movie_id = ma.movie_id where ma.actor_id=?", new Object[]{actor_id}, new BeanPropertyRowMapper<>(Movie.class));
    }

}
