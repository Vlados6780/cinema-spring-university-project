package com.example.demo.util;

import com.example.demo.dao.ActorDAO;
import com.example.demo.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ActorValidator implements Validator {
    private final ActorDAO actorDAO;

    @Autowired
    public ActorValidator(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }


    public boolean supports(Class<?> aClass) {
        return Actor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Actor actor = (Actor) o;

        if (!Character.isUpperCase(actor.getActor_name().codePointAt(0))) {
            errors.rejectValue("actor_name", "", "The name must begin with a capital letter");
        }
        if (actor.getAge() <= 1) {
            errors.rejectValue("age", "", "Age must be greater than 0.");
        }
    }

}
