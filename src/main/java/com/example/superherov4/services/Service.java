package com.example.superherov4.services;

import com.example.superherov4.dto.CityHeroDTO;
import com.example.superherov4.dto.HeroCountPowersDTO;
import com.example.superherov4.dto.HeroPowerDTO;
import com.example.superherov4.dto.SuperheroDTO;
import com.example.superherov4.model.Superhero;
import com.example.superherov4.repositories.Repository_DB;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    //stub implementering:

    //database-SQL implementering:
    private final Repository_DB repository;

    public Service(Repository_DB repository) {
        this.repository = repository;
    }

    public List<Superhero> getAllSuperheroes() {
        return repository.getAllSuperheroes();
    }

    public Superhero findSuperheroByID(int heroID) {
        return repository.findSuperheroByID(heroID);
    }

    public List<SuperheroDTO> searchForHero(String searchString) {
        return repository.searchForHero(searchString);
    }

    public List<HeroCountPowersDTO> countPowers(String searchString) {
        return repository.countPowers(searchString);
    }

    public List<HeroCountPowersDTO> countAllPowers() {
        return repository.countAllPowers();
    }

    public List<HeroPowerDTO> getSuperheroPowers(String searchString) {
        return repository.getSuperheroPowers(searchString);
        }


        public List<HeroPowerDTO> getAllSuperheroPowers() {
        return repository.getAllSuperheroPowers();
    }

    public List<CityHeroDTO> getHeroByCity(String searchString) {
        return repository.getHeroByCity(searchString);
    }

    public List<CityHeroDTO> getAllHeroByCity() {
        return repository.getAllHeroByCity();
    }
}