package com.example.superherov5.entity;

import java.util.List;

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
    }

    // Printing out if heroName is empty
    public String getHeroName() {

            return heroName;

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

    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }
}

