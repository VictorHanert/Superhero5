package com.example.superherov5.repositories;

import com.example.superherov5.dto.CityHeroDTO;
import com.example.superherov5.dto.HeroCountPowersDTO;
import com.example.superherov5.dto.HeroPowerDTO;
import com.example.superherov5.entity.Superhero;

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
