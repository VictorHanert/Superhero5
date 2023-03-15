package com.example.superherov5.repositories;

import com.example.superherov5.dto.CityHeroDTO;
import com.example.superherov5.dto.HeroCountPowersDTO;
import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.entity.Superhero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("Repository_Stub")
public class Repository_Stub implements IRepository{
    ArrayList<Superhero> superheroes = new ArrayList<>();
    ArrayList<HeroPowerDTO> heroPowerList = new ArrayList<>();
    ArrayList<HeroCountPowersDTO> heroCountPowerList = new ArrayList<>();
    ArrayList<CityHeroDTO> superheroesByCity = new ArrayList<>();

    // Test Data
     public void createTestData(){
         superheroes.add(new Superhero(1, "Bruce Wayne", "Batman", 1999, 1));
         superheroes.add(new Superhero(2, "Peter Parker", "Spider-Man", 1999, 3));
         superheroes.add(new Superhero(3, "Clark Kent", "Superman", 1999, 2));
     }

    public Repository_Stub() {
        createTestData();
    }

    @Override
    public List<Superhero> getAllSuperheroes() {
        return new ArrayList<>(superheroes);
    }

    @Override
    public Superhero findSuperheroByID(int heroID) {
        for (Superhero superhero : superheroes) {
            if (superhero.getHeroID() == (heroID)) {
                return superhero;
            }
        }
        return null;
    }

    @Override
    public List<Superhero> searchForHero(String searchString) {
        List<Superhero> searchResults = new ArrayList<>();

        for (Superhero superhero : superheroes) {
            String name = superhero.getHeroName().toLowerCase();
            if (name.contains(searchString.toLowerCase().trim())) {
                searchResults.add(superhero);
            }
        }
        return searchResults;
    }

    @Override
    public List<HeroPowerDTO> getSuperheroPowers(String searchString) {
        List<HeroPowerDTO> searchResults = new ArrayList<>();

        for (HeroPowerDTO superhero : heroPowerList) {
            String name = superhero.getHeroName().toLowerCase();

            if (name.contains(searchString.toLowerCase().trim())) {
                searchResults.add(superhero);
            }
        }
        return searchResults;
    }

    @Override
    public List<HeroPowerDTO> getAllSuperheroPowers() {
        List<HeroPowerDTO> allSuperpowers = new ArrayList<>();
        for (HeroPowerDTO superpower : heroPowerList) {
            allSuperpowers.add(new HeroPowerDTO(superpower.getHero_id(), superpower.getHeroName(), superpower.getPowerList()));
        }
        return allSuperpowers;
    }

    @Override
    public List<HeroCountPowersDTO> countPowers(String searchString) {
        return null;
    }

    @Override
    public List<HeroCountPowersDTO> countAllPowers() {
        return null;
    }

    @Override
    public List<CityHeroDTO> getHeroByCity(String searchString) {
        return null;
    }

    @Override
    public List<CityHeroDTO> getAllHeroByCity() {
        return null;
    }
}
