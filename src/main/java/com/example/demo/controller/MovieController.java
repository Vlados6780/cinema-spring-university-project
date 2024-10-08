package com.example.demo.controller;

import com.example.demo.dao.ActorDAO;
import com.example.demo.dao.MovieDAO;
import com.example.demo.entity.Movie;
import com.example.demo.util.MovieValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("movies")
public class MovieController {

    private final MovieDAO movieDAO;
    private final MovieValidator movieValidator;
    private final ActorDAO actorDAO;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("movies", movieDAO.getALl());
        return "movies/all";
    }

    @GetMapping("/new")
    public String newMovie(@ModelAttribute("movie") Movie movie) {
        return "movies/new";
    }

    @PostMapping()
    public String saveMovie(@ModelAttribute("movie") Movie movie,
                            BindingResult bindingResult) {

        movieValidator.validate(movie, bindingResult);
        movieValidator.validateNewMovie(movie, bindingResult);

        if (bindingResult.hasErrors()) {
            return "movies/new";
        }
        movieDAO.save(movie);
        return "redirect:/movies";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("movie", movieDAO.showOne(id));
        return "movies/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("movie") @Valid Movie movie,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        movieValidator.validate(movie, bindingResult);

        if (bindingResult.hasErrors()) {
            return "movies/edit";
        }

        movieDAO.update(id, movie);
        return "redirect:/movies";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        movieDAO.delete(id);
        return "redirect:/movies";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("movie", movieDAO.showOne(id));
        model.addAttribute("actors", movieDAO.findActorsByMovieId(id));
        model.addAttribute("allActors", actorDAO.getALl());
        return "movies/one";
    }
    @PostMapping("/{id}/add_actor")
    public String addActor(@PathVariable("id") int movieId, @RequestParam("actorId") int actorId) {
        movieDAO.addActorToMovie(movieId, actorId);
        return "redirect:/movies/" + movieId;
    }
}
