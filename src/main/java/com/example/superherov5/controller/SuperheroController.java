package com.example.superherov5.controller;

import com.example.superherov5.dto.CityHeroDTO;
import com.example.superherov5.dto.FormDTO;
import com.example.superherov5.dto.HeroCountPowersDTO;
import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.entity.Superhero;
import com.example.superherov5.repositories.IRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SuperheroController {

    IRepository repository;

    @Autowired
    public SuperheroController(ApplicationContext context, @Value("${superhero.repository.impl}") String impl){
        repository = (IRepository) context.getBean(impl);
    }

    @GetMapping("/add")
    public String showAddSuperheroPage(Model model) {
        FormDTO hero = new FormDTO();
        model.addAttribute("hero", hero);
        model.addAttribute("cities", repository.getCities());
        model.addAttribute("superpowers", repository.getPowers());
        model.addAttribute("powerList", new ArrayList<String>());
        return "add";
    }

    @PostMapping("/add")
    public String addSuperhero(@ModelAttribute FormDTO form) {
        repository.addSuperhero(form);
        return "redirect:/";
    }

    @GetMapping({"/",""})
    public String index(Model model){
        model.addAttribute("superhero", repository.getAllSuperheroes());
        return "index";
    }

    @GetMapping("/superpower")
    public String superpowers(Model model, HttpServletResponse response){
        model.addAttribute("superpower", repository.getAllSuperheroPowers());
        response.setHeader("Cache-Control", "max-age=3600"); // set cache max age to 1 hour

        return "superpower";
    }

    @GetMapping("/superpower/{heroName}")
    public String heroSuperpower(@PathVariable String heroName, Model model){
        model.addAttribute("superpower", repository.getSuperheroPowers(heroName));
        return "superpower";
    }

    @GetMapping("/city")
    public String city(Model model){
        model.addAttribute("city", repository.getAllHeroByCity());
        return "city";
    }

}
