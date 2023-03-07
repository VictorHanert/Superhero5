package com.example.superherov4.controller;

import com.example.superherov4.model.Superhero;
import com.example.superherov4.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("superhero")
public class SuperheroController {

    private final Service service;

    public SuperheroController(Service service) {
        this.service = service;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<Superhero>> allSuperheroes() {
        List<Superhero> superheroes = service.getSuperheroes();
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/{heroID}")
    public ResponseEntity<Superhero> findSuperheroByID(@PathVariable int heroID) {
        Superhero superhero = service.findSuperheroByID(heroID);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }
}