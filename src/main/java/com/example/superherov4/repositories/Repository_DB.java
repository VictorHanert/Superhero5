package com.example.superherov4.repositories;

import com.example.superherov4.dto.CityHeroDTO;
import com.example.superherov4.dto.HeroCountPowersDTO;
import com.example.superherov4.dto.HeroPowerDTO;
import com.example.superherov4.dto.SuperheroDTO;
import com.example.superherov4.model.Superhero;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("Repository_DB")
public class Repository_DB implements IRepository {
    List<Superhero> superheroes = new ArrayList<>();
    List<SuperheroDTO> searchList = new ArrayList<>();
    List<HeroPowerDTO> heroCountPowerList = new ArrayList<>();
    List<HeroCountPowersDTO> heroPowerList = new ArrayList<>();
    List<CityHeroDTO> superheroesByCity = new ArrayList<>();

    public List<Superhero> getAllSuperheroes() {
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

    public List<SuperheroDTO> searchForHero(String searchString) {
        String SQL = "SELECT * FROM superhero WHERE lower(heroname) LIKE ?";

        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + searchString.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int heroID = rs.getInt("hero_id");
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroName");
                int creationYear = rs.getInt("creation_year");
                searchList.add(new SuperheroDTO(heroID, realName, heroName, creationYear));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchList;
    }

    public List<HeroPowerDTO> getSuperheroPowers(String searchString) {
        String SQL = "SELECT hero_id, heroname, GROUP_CONCAT(name SEPARATOR ', ') AS superpowers " +
                "FROM superhero " +
                "LEFT JOIN superhero_power USING (hero_id)" +
                "LEFT JOIN superpower USING (power_id)" +
                "WHERE lower(heroname) LIKE ? GROUP BY hero_id;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + searchString.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int hero_id = rs.getInt("hero_id");
                String heroName = rs.getString("heroName");
                String superpowers;
                if (!(rs.getString("superpowers") == null)) {
                    superpowers = rs.getString("superpowers");
                }
                else {
                    superpowers = "Hero has no superpowers";
                }
                heroCountPowerList.add(new HeroPowerDTO(hero_id, heroName, new ArrayList<>(List.of(superpowers))));
            }
            return heroCountPowerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HeroPowerDTO> getAllSuperheroPowers() {
        String SQL = "SELECT hero_id, heroname, GROUP_CONCAT(name SEPARATOR ', ') AS superpowers " +
                "FROM superhero " +
                "LEFT JOIN superhero_power USING (hero_id)" +
                "LEFT JOIN superpower USING (power_id)" +
                "GROUP BY hero_id;";
        try {
            Statement stmt = DbManager.getConnection().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                int hero_id = rs.getInt("hero_id");
                String heroName = rs.getString("heroName");
                String superpowers;
                if (!(rs.getString("superpowers") == null)) {
                    superpowers = rs.getString("superpowers");
                }
                else {
                    superpowers = "Hero has no superpowers";
                }
                heroCountPowerList.add(new HeroPowerDTO(hero_id, heroName, new ArrayList<>(List.of(superpowers))));
            }
            return heroCountPowerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HeroCountPowersDTO> countPowers(String searchString) {
        String SQL = "SELECT heroName, realName, COUNT(power_id) AS powerCount FROM superhero " +
                "JOIN superhero_power USING (hero_id) " +
                "WHERE lower(heroName) LIKE ? GROUP BY hero_id, heroName;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + searchString.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String realName = rs.getString("realName");
                int count = rs.getInt("powerCount");
                heroPowerList.add(new HeroCountPowersDTO(heroName, realName, count));
            }
            return heroPowerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HeroCountPowersDTO> countAllPowers() {
        String SQL = "SELECT heroName, realName, COUNT(power_id) AS powerCount FROM superhero " +
                "JOIN superhero_power USING (hero_id) " +
                "GROUP BY hero_id, heroName;";
        try {
            Statement stmt = DbManager.getConnection().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String realName = rs.getString("realName");
                int count = rs.getInt("powerCount");
                heroPowerList.add(new HeroCountPowersDTO(heroName, realName, count));
            }
            return heroPowerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CityHeroDTO> getHeroByCity(String searchString) {
        String SQL = "SELECT heroName, name AS cityName FROM superhero " +
                "JOIN city USING (city_id) " +
                "WHERE heroName LIKE ?" +
                "GROUP BY hero_id;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + searchString.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String cityName = rs.getString("cityName");
                superheroesByCity.add(new CityHeroDTO(heroName, cityName));
            }
            return superheroesByCity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CityHeroDTO> getAllHeroByCity() {
        String SQL = "SELECT heroName, name AS cityName FROM superhero JOIN city USING (city_id);";

        try {
            Statement stmt = DbManager.getConnection().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String heroName = rs.getString("heroName");
                String cityName = rs.getString("cityName");
                superheroesByCity.add(new CityHeroDTO(heroName, cityName));
            }
            return superheroesByCity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}