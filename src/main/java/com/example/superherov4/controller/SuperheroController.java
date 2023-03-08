package com.example.superherov4.controller;

import com.example.superherov4.dto.CityHeroDTO;
import com.example.superherov4.dto.HeroCountPowersDTO;
import com.example.superherov4.dto.HeroPowerDTO;
import com.example.superherov4.dto.SuperheroDTO;
import com.example.superherov4.model.Superhero;
import com.example.superherov4.services.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("superhero")
public class SuperheroController {

    private final Service service;

    public SuperheroController(Service service) {
        this.service = service;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<Superhero>> getAllSuperheroes() {
        List<Superhero> superheroes = service.getAllSuperheroes();
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/ID/{heroID}")
    public ResponseEntity<Superhero> findSuperheroByID(@PathVariable int heroID) {
        Superhero superhero = service.findSuperheroByID(heroID);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }

    @GetMapping(path = "/{heroName}")
    public ResponseEntity<List<SuperheroDTO>> searchForHero
            (@PathVariable(required = false) String heroName) {
        List<SuperheroDTO> superheroes = service.searchForHero(heroName);
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/count/{heroName}")
    public ResponseEntity<List<HeroCountPowersDTO>> countPowers(@PathVariable String heroName) {
        List<HeroCountPowersDTO> superheroes = service.countPowers(heroName);
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/{heroName}")
    public ResponseEntity<List<HeroPowerDTO>> getSuperheroPowers(@PathVariable String heroName) {
        List<HeroPowerDTO> superheroes = service.getSuperheroPowers(heroName);
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/city/{heroName}")
    public ResponseEntity<List<CityHeroDTO>> getHeroByCity(@PathVariable String heroName) {
        List<CityHeroDTO> superheroes = service.getHeroByCity(heroName);
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

}
