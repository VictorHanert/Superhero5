package com.example.superherov4.dto;

public class SuperheroDTO {
    private int heroID;
    private String realName;
    private String heroName;
    private int creationYear;

    public SuperheroDTO(int heroID, String realName, String heroName, int creationYear) {
        this.heroID = heroID;
        this.realName = realName;
        this.heroName = heroName;
        this.creationYear = creationYear;
    }

    public int getHeroID() {
        return heroID;
    }

    public String getRealName() {
        return realName;
    }

    public String getHeroName() {
        return heroName;
    }

    public int getCreationYear() {
        return creationYear;
    }
}
