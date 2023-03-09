package com.example.superherov4.controller;

import com.example.superherov4.dto.CityHeroDTO;
import com.example.superherov4.dto.HeroCountPowersDTO;
import com.example.superherov4.dto.HeroPowerDTO;
import com.example.superherov4.dto.SuperheroDTO;
import com.example.superherov4.model.Superhero;
import com.example.superherov4.repositories.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("superhero")
public class SuperheroController {

    IRepository repository;

    @Autowired
    public SuperheroController(ApplicationContext context, @Value("${superhero.repository.impl}") String impl){
        repository = (IRepository) context.getBean(impl);
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<List<Superhero>> getAllSuperheroes() {
        List<Superhero> superheroes = repository.getAllSuperheroes();
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/ID/{heroID}")
    public ResponseEntity<Superhero> findSuperheroByID(@PathVariable int heroID) {
        Superhero superhero = repository.findSuperheroByID(heroID);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }

    @GetMapping(path = "/{heroName}")
    public ResponseEntity<List<Superhero>> searchForHero
            (@PathVariable(required = false) String heroName) {
        List<Superhero> superheroes = repository.searchForHero(heroName);
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = {"/superpower/count/","/superpower/count"})
    public ResponseEntity<List<HeroCountPowersDTO>> countAllPowers() {
        List<HeroCountPowersDTO> superheroes = repository.countAllPowers();
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/count/{heroName}")
    public ResponseEntity<List<HeroCountPowersDTO>> countPowers(@PathVariable String heroName) {
        List<HeroCountPowersDTO> superheroes = repository.countPowers(heroName);
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = {"/superpower/","/superpower"})
    public ResponseEntity<List<HeroPowerDTO>> getAllSuperheroPowers() {
        List<HeroPowerDTO> superheroes = repository.getAllSuperheroPowers();
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/superpower/{heroName}")
    public ResponseEntity<List<HeroPowerDTO>> getSuperheroPowers(@PathVariable String heroName) {
        List<HeroPowerDTO> superheroes = repository.getSuperheroPowers(heroName);
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = {"/city/", "/city"})
    public ResponseEntity<List<CityHeroDTO>> getAllHeroByCity() {
        List<CityHeroDTO> superheroes = repository.getAllHeroByCity();
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

    @GetMapping(path = "/city/{heroName}")
    public ResponseEntity<List<CityHeroDTO>> getHeroByCity(@PathVariable String heroName) {
        List<CityHeroDTO> superheroes = repository.getHeroByCity(heroName);
        return new ResponseEntity<>(superheroes, HttpStatus.OK);
    }

}
