package com.example.demo.dao;

import com.example.demo.entity.Actor;
import com.example.demo.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface ActorDAO {
    List<Actor> getALl();
    void save(Actor actor);
    Actor showOne(int actor_id);
    void update(int actor_id, Actor actor);
    void delete(int actor_id);
    List<Movie> findMoviesByActorId(int actor_id);
}
