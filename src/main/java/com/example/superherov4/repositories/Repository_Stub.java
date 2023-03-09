package com.example.superherov4.repositories;

import com.example.superherov4.model.Superhero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("Repository_Stub")
public class Repository_Stub {
    ArrayList<Superhero> superheroes = new ArrayList<>();

    // Test Data
     public void createTestData(){
         superheroes.add(new Superhero(1, "Batman", "Bruce Wayne", 1999, 1));
         superheroes.add(new Superhero(2, "Spider-Man", "Peter Parker", 1999, 3));
         superheroes.add(new Superhero(3, "Superman", "Clark Kent", 1999, 2));

     }
    public Repository_Stub() {
        createTestData();
    }

    public List<Superhero> getAllSuperheroes() {
        return new ArrayList<>(superheroes);
    }

    public List<Superhero> searchForHero(String searchTerm) {
        List<Superhero> searchResults = new ArrayList<>();

        for (Superhero superhero : superheroes) {
            String name = superhero.getHeroName().toLowerCase();
            if (name.contains(searchTerm.toLowerCase().trim())) {
                searchResults.add(superhero);
            }
        }
        return searchResults;
    }
}
