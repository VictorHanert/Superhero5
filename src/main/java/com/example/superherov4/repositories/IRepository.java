package com.example.superherov4.repositories;

import com.example.superherov4.dto.CityHeroDTO;
import com.example.superherov4.dto.HeroCountPowersDTO;
import com.example.superherov4.dto.HeroPowerDTO;
import com.example.superherov4.model.Superhero;

import java.util.List;

public interface IRepository {
    List<Superhero> getAllSuperheroes();

    Superhero findSuperheroByID(int heroID);

    List<Superhero> searchForHero(String searchString);

    List<HeroPowerDTO> getSuperheroPowers(String searchString);

    List<HeroPowerDTO> getAllSuperheroPowers();

    List<HeroCountPowersDTO> countPowers(String searchString);

    List<HeroCountPowersDTO> countAllPowers();

    List<CityHeroDTO> getHeroByCity(String searchString);

    List<CityHeroDTO> getAllHeroByCity();
}
