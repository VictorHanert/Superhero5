package com.example.superherov4.repositories;

import com.example.superherov4.dto.CityHeroDTO;
import com.example.superherov4.dto.HeroCountPowersDTO;
import com.example.superherov4.dto.HeroPowerDTO;
import com.example.superherov4.model.Superhero;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Repository_DB {

    public List<Superhero> getAllSuperheroes() {
        List<Superhero> superheroes = new ArrayList<>();
        String SQL = "SELECT * FROM superhero;";

        try {
            Statement stmt = DbManager.getConnection().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int ID = rs.getInt("hero_id");
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroName");
                int creationYear = rs.getInt("creation_year");
                int cityID = rs.getInt("city_id");
                superheroes.add(new Superhero(ID, realName, heroName, creationYear, cityID));
            }
            return superheroes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Superhero findSuperheroByID(int heroID) {
        String SQL = "SELECT * FROM superhero WHERE hero_id = ?;";

        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setInt(1, heroID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int heroId = rs.getInt("hero_id");
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroname");
                int creationYear = rs.getInt("creation_year");
                int cityID = rs.getInt("city_id");
                return new Superhero(heroId, realName, heroName, creationYear, cityID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Superhero> searchForHero(String searchString) {
        String SQL = "SELECT * FROM superhero WHERE lower(heroname) LIKE ?";
        List<Superhero> searchList = new ArrayList<>();

        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + searchString.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int heroId = rs.getInt("hero_id");
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroName");
                int creationYear = rs.getInt("creation_year");
                int cityId = rs.getInt("city_id");
                Superhero superhero = new Superhero(heroId, realName, heroName, creationYear, cityId);
                searchList.add(superhero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public List<HeroPowerDTO> getSuperheroPowers(String searchString) {
        List<HeroPowerDTO> heroPowerList = new ArrayList<>();
        String SQL = "SELECT hero_id, heroname, GROUP_CONCAT(name SEPARATOR ', ') AS superpowers " +
                "FROM superhero " +
                "LEFT JOIN superhero_power USING (hero_id)" +
                "LEFT JOIN superpower USING (power_id)" +
                "WHERE heroname = ?;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, searchString);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int hero_id = rs.getInt("hero_id");
                String heroName = rs.getString("heroName");
                String superpowers = rs.getString("superpowers");
                heroPowerList.add(new HeroPowerDTO(hero_id, heroName, new ArrayList<>(List.of(superpowers))));
            }
            return heroPowerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HeroCountPowersDTO> countPowers(String searchString) {
        List<HeroCountPowersDTO> heroPowerList = new ArrayList<>();
        String SQL = "SELECT heroName, realName, COUNT(power_id) AS powerCount FROM superhero " +
                "JOIN superhero_power USING (hero_id) " +
                "WHERE heroName = ? GROUP BY hero_id, heroName;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, searchString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String realName = rs.getString("realName");
                int count = rs.getInt("powerCount");
                HeroCountPowersDTO dto = new HeroCountPowersDTO(heroName, realName, count);
                heroPowerList.add(dto);
            }
            return heroPowerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CityHeroDTO> getHeroByCity(String searchString) {
        List<CityHeroDTO> heroCityList = new ArrayList<>();
        String SQL = "SELECT heroName, name AS cityName FROM superhero JOIN city USING (city_id) WHERE heroName = ?;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, searchString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String cityName = rs.getString("cityName");
                CityHeroDTO dto = new CityHeroDTO(heroName, cityName);
                heroCityList.add(dto);
            }
            return heroCityList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}