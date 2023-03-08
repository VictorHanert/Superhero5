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
        List<Superhero> superheroes = repository.searchForHero(searchString);
        List<SuperheroDTO> searchList = new ArrayList<>();
        for (Superhero superhero : superheroes) {
            searchList.add(new SuperheroDTO(superhero.getRealName(), superhero.getHeroName(), superhero.getCreationYear()));
        }
        return searchList;
    }

    public List<HeroCountPowersDTO> countPowers(String heroName) {
        return repository.countPowers(heroName);
    }

    public List<HeroPowerDTO> getSuperheroPowers(String heroName) {
        return repository.getSuperheroPowers(heroName);
    }

    public List<CityHeroDTO> getHeroByCity(String heroName) {
        return repository.getHeroByCity(heroName);
    }
}