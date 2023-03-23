package com.example.superherov5.repositories;

import com.example.superherov5.dto.*;
import com.example.superherov5.entity.Superhero;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("Repository_DB")
public class Repository_DB implements IRepository {
    List<Superhero> superheroes = new ArrayList<>();
    List<HeroPowerDTO> heroPowerList = new ArrayList<>();
    List<HeroCountPowersDTO> heroCountPowerList = new ArrayList<>();
    List<CityHeroDTO> superheroesByCity = new ArrayList<>();

    public List<Superhero> getAllSuperheroes() {
        String SQL = "SELECT * FROM superhero;";

        try {
            Statement stmt = DbManager.getConnection().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            superheroes.clear();
            while (rs.next()) {
                int ID = rs.getInt("hero_id");
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroName");
                int creationYear = rs.getInt("creation_year");
                int cityID = rs.getInt("city_id");
                superheroes.add(new Superhero(ID, realName, heroName, creationYear, cityID));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return superheroes;
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

        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, "%" + searchString.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            superheroes.clear();
            while (rs.next()) {
                int heroID = rs.getInt("hero_id");
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroName");
                int creationYear = rs.getInt("creation_year");
                int cityID = rs.getInt("city_id");
                superheroes.add(new Superhero(heroID, realName, heroName, creationYear, cityID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return superheroes;
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

            heroPowerList.clear();

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
                heroPowerList.add(new HeroPowerDTO(hero_id, heroName, new ArrayList<>(List.of(superpowers))));
            }
            return heroPowerList;
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

            heroPowerList.clear();

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
                heroPowerList.add(new HeroPowerDTO(hero_id, heroName, new ArrayList<>(List.of(superpowers))));
            }
            return heroPowerList;
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
                heroCountPowerList.add(new HeroCountPowersDTO(heroName, realName, count));
            }
            return heroCountPowerList;
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
                heroCountPowerList.add(new HeroCountPowersDTO(heroName, realName, count));
            }
            return heroCountPowerList;
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
            superheroesByCity.clear();

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
            superheroesByCity.clear();

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

    public void addSuperhero(FormDTO form) {

        try {
            // ID's
            int cityId = 0;
            int heroId = 0;
            List<Integer> powerIDs = new ArrayList<>();

            // find city_id
            String SQL1 = "select city_id from city where name = ?;";
            PreparedStatement pstmt = DbManager.getConnection().prepareStatement(SQL1);
            pstmt.setString(1, form.getCity());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cityId = rs.getInt("city_id");
                System.out.println(cityId);
            }

            // insert row in superhero table
            String SQL2 = "insert into superhero (heroname, realName, creation_year, city_id) " +
                    "values(?, ?, ?, ?);";
            // return autoincremented key:
            pstmt = DbManager.getConnection().prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, form.getHeroName());
            pstmt.setString(2, form.getRealName());
            pstmt.setInt(3, form.getCreationYear());
            pstmt.setInt(4, cityId);

            int rows = pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                heroId = rs.getInt(1);
            }

            // find power_ids
            String SQL3 = "select power_id from superpower where name = ?;";
            pstmt = DbManager.getConnection().prepareStatement(SQL3);

            for (String power : form.getPowerList()) {
                pstmt.setString(1, power);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    powerIDs.add(rs.getInt("power_id"));
                }
            }

            // insert entries in superhero_powers join table
            String SQL4 = "insert into superhero_power values (?,?,'high');";
            pstmt = DbManager.getConnection().prepareStatement(SQL4);

            for (Integer powerID : powerIDs) {
                pstmt.setInt(1, heroId);
                pstmt.setInt(2, powerID);
                rows = pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getCities() {
        String SQL = "select name from city;";
        List<String> cities = new ArrayList<>();
        try {
            Statement stmt = DbManager.getConnection().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String cityName = rs.getString("name");
                cities.add(cityName);
            }
            return cities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getPowers() {
        String SQL = "select name from superpower;";
        List<String> superpowers = new ArrayList<>();
        try {
            Statement stmt = DbManager.getConnection().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String powerName = rs.getString("name");
                superpowers.add(powerName);
            }
            return superpowers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSuperhero(Integer heroID) {
        String SQL = "DELETE FROM superhero WHERE hero_id = ?;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setInt(1, heroID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Superhero with ID " + heroID + " has been deleted.");
            } else {
                System.out.println("No superhero with ID " + heroID + " found to delete.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editSuperhero(Superhero superhero) {
        String SQL = "UPDATE superhero\n" +
                "SET heroname=?, realName=?, creation_year=?, city_id=?\n" +
                "WHERE hero_id = ?;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, superhero.getHeroName());
            ps.setString(2, superhero.getRealName());
            ps.setInt(3, superhero.getCreationYear());
            ps.setInt(4, superhero.getCityID());
            ps.setInt(5, superhero.getHeroID());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Superhero with ID " + superhero.getHeroID() + " has been edited.");
            } else {
                System.out.println("No superhero with ID " + superhero.getHeroID() + " found to edit.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}