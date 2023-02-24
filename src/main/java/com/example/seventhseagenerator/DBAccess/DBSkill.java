package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSkill {

    /**
     * Creates skill entries in database
     * For use in DBCreate class
     */
    //Format:
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    public static void createSkills() {
        try {
            String sql = "INSERT INTO 'skills' " +
                    "              SELECT '1' AS 'id', 'Courtier' AS 'name', 'Courtiers are skilled in the diplomatic " +
                    "arts. As a member of this esteemed profession you can dine with kings, chat pleasantly with " +
                    "Cardinals or, when required, pull state secrets from your own lovers lips.' AS 'description' " +
                    "UNION ALL SELECT '2', 'Performer', 'Performers often earn their living by making crowds of people " +
                    "laugh, cry, and cheer. Whatever comes back when they pass the hat is often all they have to eat " +
                    "with that night. Because the take is so small (or maybe just because they can), some performers " +
                    "turn their talents to other (less honest) purposes.' " +
                    "UNION ALL SELECT '3', 'Dirty Fighting', 'The niceties of pugilism are not for everyone. Some folks " +
                    "think that fair play is a good way to get killed.' " +
                    "UNION ALL SELECT '4', 'Fencing', 'Heroes trained in Fencing have a basic understanding of the " +
                    "theories and techniques of modern swordplay. This skill trains the Hero in the use of fencing " +
                    "weapons such as the rapier, foil, smallsword and epee. It is the foundation for most of the " +
                    "training presented in Theahs Swordsman Schools.'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a list of all skills
     *
     * @return ObservalbeList of Skill objects
     */
    public static ObservableList<Skill> getAllSkills() {
        ObservableList<Skill> skills = FXCollections.observableArrayList();
        try {
            String sql = "SELECT S.id, S.name " +
                    "FROM skills S";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Skill skill = new Skill(id, name);
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    /**
     * Returns an ObservableList of skills that contains in the name the String searched for
     *
     * @param q String with wildcard
     * @return ObservableList<Skill> of matching Skills
     */
    //How to use a wildcard in a SELECT query in Java prepared statement here:
//https://stackoverflow.com/questions/8247970/using-like-wildcard-in-prepared-statement
    public static ObservableList<Skill> lookupSkill(String q) {
        ObservableList<Skill> skills = FXCollections.observableArrayList();
        try {
            String sql = "SELECT S.id, S.name " +
                    "FROM skills S " +
                    "WHERE S.name LIKE ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, "%" + q + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Skill skill = new Skill(id, name);
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;

    }

    /**
     * Returns skill by id
     *
     * @param skillId the id of the skill to search for
     * @return Skill with id matching input
     */
    public static Skill lookupSkillById(int skillId) {
        Skill searchedSkill = null;
        try {
            String sql = "SELECT id, name " +
                    "FROM skills " +
                    "WHERE skills.id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, skillId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                searchedSkill = new Skill(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searchedSkill;
    }
}
