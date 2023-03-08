package com.example.superherov4.model;

public class Superhero {
    // attributes
    private int heroID;
    private String realName;
    private String heroName;
    private int creationYear;
    private int cityID;


    // constructor
    public Superhero(int heroID, String realName, String heroName, int creationYear, int cityID) {
        this.heroID = heroID;
        this.realName = realName;
        this.heroName = heroName;
        this.creationYear = creationYear;
        this.cityID = cityID;
    }

    public Superhero() {
        //empty
    }

    public Superhero(String realName, String heroName, int creationYear) {
        this.realName = realName;
        this.heroName = heroName;
        this.creationYear = creationYear;
    }

    // Printing out if heroName is empty
    public String getHeroName() {
        if (heroName.isEmpty()) {
            return "Intet superhelte navn";
        } else {
            return heroName;
        }
    }

    public int getHeroID() {
        return heroID;
    }

    public int getCityID() {
        return cityID;
    }

    public String getRealName() {
        return realName;
    }

    public int getCreationYear() {
        return creationYear;
    }
}

