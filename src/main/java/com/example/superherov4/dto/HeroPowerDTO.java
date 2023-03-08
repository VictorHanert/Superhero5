package com.example.superherov4.dto;

import java.util.List;

public class HeroPowerDTO {
    private String heroName;
    private String realName;
    List<String> powerList;

    public HeroPowerDTO(String heroName, String realName, List<String> powerList) {
        this.heroName = heroName;
        this.realName = realName;
        this.powerList = powerList;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public List<String> getPowerList() {
        return powerList;
    }

    public void add(String power) {
        powerList.add(power);
    }
}
