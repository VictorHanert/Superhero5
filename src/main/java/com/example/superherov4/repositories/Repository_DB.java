package com.example.superherov4.repositories;

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

    public Superhero findSuperheroByName(String heroname) {
        String SQL = "SELECT * FROM superhero WHERE heroname = ?;";

        try {
            PreparedStatement ps = DbManager.getConnection().prepareStatement(SQL);
            ps.setString(1, heroname);
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
}
