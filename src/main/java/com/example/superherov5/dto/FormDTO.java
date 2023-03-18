package com.example.superherov5.dto;

import java.util.List;

public class FormDTO {
        private int heroId;
        private String heroName;
        private String realName;
        private int creationYear;
        private String city;
        List<String> powerList;

        public FormDTO(int heroId, String heroName, String realName,
                                int creationYear, String city, List<String> powerList) {
            this.heroId = heroId;
            this.heroName = heroName;
            this.realName = realName;
            this.creationYear = creationYear;
            this.city = city;
            this.powerList = powerList;
        }
        public FormDTO() {
            // default konstruktør skal laves
        }

    public FormDTO(String heroName, String realName, int creationYear, String city, List<String> powerList) {
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
        this.city = city;
        this.powerList = powerList;
    }

    public void add(String power) {
            powerList.add(power);
        }

        //Getter og setter:

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<String> powerList) {
        this.powerList = powerList;
    }
}
