package com.example.superherov4.services;

import com.example.superherov4.model.Superhero;
import com.example.superherov4.repositories.Repository_DB;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    //database-SQL implementering
    private final Repository_DB repository;

    public Service(Repository_DB repository) {
        this.repository = repository;
    }

    public List<Superhero> getSuperheroes() {
        return repository.getSuperheroes();
    }

    public Superhero findSuperheroByID(int heroID) {
        return repository.findSuperheroByID(heroID);
    }

}