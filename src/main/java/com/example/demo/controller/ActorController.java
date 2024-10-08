package com.example.demo.controller;

import com.example.demo.dao.ActorDAO;
import com.example.demo.entity.Actor;
import com.example.demo.util.ActorValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("actors")
public class ActorController {

    private final ActorDAO actorDAO;
    private final ActorValidator actorValidator;

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("actors", actorDAO.getALl());
        return "actors/all";
    }

    @GetMapping("/new")
    public String newActor(@ModelAttribute("actor") Actor actor) {
        return "actors/new";
    }

    @PostMapping()
    public String saveActor(@ModelAttribute("actor") Actor actor,
                            BindingResult bindingResult) {
        actorValidator.validate(actor, bindingResult);
        if (bindingResult.hasErrors()) {
            return "actors/new";
        }
        actorDAO.save(actor);
        return "redirect:/actors";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("actor", actorDAO.showOne(id));
        return "actors/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("actor") @Valid Actor actor,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        actorValidator.validate(actor, bindingResult);

        if (bindingResult.hasErrors()) {
            return "actors/edit";
        }

        actorDAO.update(id, actor);
        return "redirect:/actors";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        actorDAO.delete(id);
        return "redirect:/actors";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model) {
        model.addAttribute("actor", actorDAO.showOne(id));
        model.addAttribute("movies", actorDAO.findMoviesByActorId(id));
        return "actors/one";
    }


}
