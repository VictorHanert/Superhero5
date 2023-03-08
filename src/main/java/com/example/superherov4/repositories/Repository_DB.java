package com.example.superherov4.repositories;

import com.example.superherov4.dto.HeroPowerDTO;
import com.example.superherov4.dto.SuperheroDTO;
import com.example.superherov4.model.Superhero;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Repository_DB {

    public List<Superhero> getSuperheroes() {
        List<Superhero> superheroes = new ArrayList<>();
        String SQL = "SELECT * FROM superhero;";

        try {
            Statement stmt = DbManager.getConnection().prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                int ID = rs.getInt("hero_id");
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroname");
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

    public SuperheroDTO findSuperheroByName(String heroname) {
        String SQL = "SELECT heroname, realName, creation_year FROM superhero WHERE heroname = ?;";

        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, heroname);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String realName = rs.getString("realName");
                String heroName = rs.getString("heroname");
                int creationYear = rs.getInt("creation_year");
                return new SuperheroDTO(realName, heroName, creationYear);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HeroPowerDTO> getSuperheroPowers(String heroname){
        List<HeroPowerDTO> heroPowerList = new ArrayList<>();
        String SQL = "SELECT hero_id, heroname, GROUP_CONCAT(name SEPARATOR ', ') AS superpowers " +
                "FROM superhero " +
                "LEFT JOIN superhero_power using (hero_id)" +
                "LEFT JOIN superpower using (power_id)" +
                "WHERE heroname = ?;";
        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, heroname);
            ResultSet rs = ps.executeQuery();

            String currentName = "";
            HeroPowerDTO newPowerDTO = null;
            while (rs.next()) {
                int hero_id = rs.getInt("hero_id");
                String heroName = rs.getString("heroname");
                String superpowers = rs.getString("superpowers");

                if (!currentName.equals(heroName)){
                    newPowerDTO = new HeroPowerDTO(hero_id, heroName, new ArrayList<>(List.of(superpowers)));
                    heroPowerList.add(newPowerDTO);
                    currentName = heroName;
                }

            } return heroPowerList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
