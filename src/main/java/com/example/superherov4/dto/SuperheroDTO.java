package com.example.superherov4.dto;

public class SuperheroDTO {
    private String realName;
    private String heroName;
    private int creationYear;

    public SuperheroDTO(String realName, String heroName, int creationYear) {
        this.realName = realName;
        this.heroName = heroName;
        this.creationYear = creationYear;
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
