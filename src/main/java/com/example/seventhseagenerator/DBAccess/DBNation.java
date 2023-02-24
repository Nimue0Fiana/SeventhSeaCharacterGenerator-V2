package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.Nation;
import com.example.seventhseagenerator.Models.Sorcery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBNation {
    /**
     * Creates all nations in the database.
     * For use by the DBCreate class
     */
    //How to insert multiple rows using a single query found here:
//https://stackoverflow.com/questions/1609637/is-it-possible-to-insert-multiple-rows-at-a-time-in-an-sqlite-database/1734067#1734067
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    public static void createNations() {
        try {
            String sql = "INSERT INTO 'nations' " +
                    "SELECT '1' AS 'id', 'Avalon' AS 'name', 'Resolve' AS 'favored_trait', 'Green and enchanted, this union of three " +
                    "kingdoms has recently risen to the forefront of Thean politics.' AS 'description', '1' AS 'sorcery_id' " +
                    "UNION ALL SELECT '2' AS 'id', 'Castille' AS 'name', 'Finesse' AS 'favored_trait', 'Headquarters of the Vaticine church," +
                    " this fertile nation has recently fallen under attack from the Montaigne to the north.' AS 'description', null AS 'sorcery_id' " +
                    "UNION ALL SELECT '3' AS 'id', 'Montaigne' AS 'name', 'Panache' AS 'favored_trait', 'One of Theahs most powerful nations, " +
                    "leading the world in art and culture even as its Emperor crushes the populace beneath his thumb.' AS 'description', " +
                    "'2' AS 'sorcery_id' " +
                    "UNION ALL SELECT '4' AS 'id', 'Vodacce' AS 'name', 'Wits' AS 'favored_trait', 'The former cradle of civilization, " +
                    "now split between seven merchant Princes whose complex schemes reach every corner of the world. ONLY " +
                    "VODACCE WOMEN CAN ACCESS THE VODACCE SORCERY' AS 'description', '3' AS 'sorcery_id'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an Observable List of all nations
     *
     * @return Observable List containing all nations in database
     */
    public static ObservableList<Nation> getAllNations() {
        ObservableList<Nation> nationsList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT N.id, N.name, N.favored_trait, N.description " +
                    "FROM nations N";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(("id"));
                String name = rs.getString("name");
                String favored_trait = rs.getString("favored_trait");
                String description = rs.getString("description");

                Nation nation = new Nation(id, name, favored_trait, description);
                nationsList.add(nation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nationsList;
    }

    /**
     * Returns String description of a nation given a String name input
     *
     * @param name name of nation to find the description for
     * @return String description of searched Nation
     */
    public static String getNationDescByName(String name) {
        String description;
        try {
            String sql = "SELECT N.description " +
                    "FROM nations N " +
                    "WHERE N.name = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            description = rs.getString("description");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return description;
    }

    /**
     * Gets Nation object from String name
     *
     * @param name name of nation to return
     * @return Nation object with matching name
     */
    public static Nation getNationByName(String name) {
        Nation requestedNation = null;
        int sorcery_id = 0;
        Sorcery sorcery = null;
        try {
            String sql = "SELECT N.id, N.name, N.favored_trait, N.description, N.sorcery_id " +
                    "FROM nations N " +
                    "WHERE N.name = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nationName = rs.getString("name");
                String favored_trait = rs.getString("favored_trait");
                String description = rs.getString("description");
                sorcery_id = rs.getInt("sorcery_id");
                requestedNation = new Nation(id, nationName, favored_trait, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String sql = "SELECT S.id, S.name, S.description " +
                    "FROM sorceries S " +
                    "WHERE S.id = " + sorcery_id;

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String sorceryName = rs.getString("name");
                String description = rs.getString("description");
                sorcery = new Sorcery(id, sorceryName, description);

                requestedNation.setSorcery(sorcery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestedNation;
    }

    /**
     * Returns Nation object from an id
     *
     * @param id id of Nation object to search for
     * @return Nation object matching id input
     */
    public static Nation getNationById(int id) {
        Nation requestedNation = null;
        try {
            String sql = "SELECT N.id, N.name, N.favored_trait, N.description " +
                    "FROM nations N " +
                    "WHERE N.id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int nationId = rs.getInt("id");
                String nationName = rs.getString("name");
                String favored_trait = rs.getString("favored_trait");
                String description = rs.getString("description");
                requestedNation = new Nation(id, nationName, favored_trait, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestedNation;
    }
}
