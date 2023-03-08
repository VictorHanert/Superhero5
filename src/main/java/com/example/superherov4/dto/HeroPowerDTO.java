package com.example.superherov4.dto;

import java.util.List;

public class HeroPowerDTO {
    private int hero_id;
    private String heroName;
    List<String> powerList;

    public HeroPowerDTO(int hero_id, String heroName, List<String> powerList) {
        this.hero_id = hero_id;
        this.heroName = heroName;
        this.powerList = powerList;
    }

    public int getHero_id() {
        return hero_id;
    }

    public String getHeroName() {
        return heroName;
    }

    public List<String> getPowerList() {
        return powerList;
    }

    public void add(String power) {
        powerList.add(power);
    }
}
