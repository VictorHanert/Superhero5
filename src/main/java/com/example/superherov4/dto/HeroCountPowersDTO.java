package com.example.superherov4.dto;

public class HeroCountPowersDTO {
    private String heroname;
    private String realName;
    private int powerCount;

    public HeroCountPowersDTO(String heroname, String realName, int powerCount) {
        this.heroname = heroname;
        this.realName = realName;
        this.powerCount = powerCount;
    }

    public String getHeroname() {
        return heroname;
    }

    public String getRealName() {
        return realName;
    }

    public int getPowerCount() {
        return powerCount;
    }
}
