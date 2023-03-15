package com.example.superherov5.dto;

public class CityHeroDTO {
    private String heroName;
    private String city_name;

    public CityHeroDTO(String heroName, String city_name) {
        this.heroName = heroName;
        this.city_name = city_name;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getCity_name() {
        return city_name;
    }
}
