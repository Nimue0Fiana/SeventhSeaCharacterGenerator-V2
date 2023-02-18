package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.Nation;
import com.example.seventhseagenerator.Models.SwordsmanSchool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSwordsmanSchool {
    /**
     * Creates swordsman school entries in the database
     * For use in DBCreate Class
     */
    //Format:
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    public static void createSwordsmanSchools() {
        try {
            String sql = "INSERT INTO 'swordsman_schools' " +
                    "SELECT '1' AS 'is', '1' AS 'nation_id', 'Donovan' AS 'name', 'This style is somewhat old-fashioned, " +
                    "using a bucker and smallsword instead of the more modern rapier and main gauche. This style teaches " +
                    "a wide variety of slashes and thrusts, which tends to confuse those trained to fight against " +
                    "thrusts almost exclusively.' AS 'description' " +
                    "UNION ALL SELECT '2', '2', 'Aldana', 'Aldana is designed for use with fencing weapons. The fencers " +
                    "hand is tucked behind their back. Aldana combines sword fighting with dancing to produce an " +
                    "elusive, unpredictable series of movements. The duelist silently counts time inside his head, " +
                    "mentally playing the song that he is dancing to. This allows him to make unpredictable moves along " +
                    "with the rhythm of the song, which is unknown to his opponent.' " +
                    "UNION ALL SELECT '3', '3', 'Valroux', 'The Valroux style is one of several that uses a fencing " +
                    "weapon in the primary hand and a main gauche in the off-hand. The fighting style is technically " +
                    "defensive, with the main gauche used only to parry. Students of this style are prone to teasing " +
                    "their opponents. They call attention to openings they could have exploited, generally humiliate " +
                    "their opponents and then finish them off whenever the duel grows wearisome.' " +
                    "UNION ALL SELECT '4', '4', 'Ambrogia', 'Ambrogia teaches its students to fight with their sword in " +
                    "their left hand, and their main gauche in their right. While Ambrogia does focus on the left hand, " +
                    "it emphasizes practicality over style.' ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a String description of a swordsman school with a searched id
     *
     * @param id the id of the school to return a description for
     * @return String description of the school selected
     */
    public static String getSwordSchoolDescById(int id) {
        String description;
        try {
            String sql = "SELECT S.description " +
                    "FROM swordsman_schools S " +
                    "WHERE S.nation_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            description = rs.getString("description");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return description;
    }

    /**
     * Returns a String name of the school with selected id
     *
     * @param id the id of the school to get the name for
     * @return String name of the school having matching id
     */
    public static String getSwordSchoolNameById(int id) {
        String name;
        try {
            String sql = "SELECT S.name " +
                    "FROM swordsman_schools S " +
                    "WHERE S.nation_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            name = rs.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    /**
     * Returns an ObservableList of Swordsman Schools from countries NOT of the country id specified
     *
     * @param id the id of the nation to not put in the list
     * @return ObservableList<SwordsmanSchool> of schools not belonging to the nation specified
     */
    public static ObservableList<SwordsmanSchool> getNonNativeSwordSchool(int id) {
        ObservableList<SwordsmanSchool> otherSwordSchools = FXCollections.observableArrayList();
        try {
            String sql = "SELECT S.id school_id, S.name, S.description, S.nation_id, N.name nation_name, N.DESCRIPTION nation_description, N.FAVORED_TRAIT " +
                    "FROM swordsman_schools S " +
                    "JOIN nations N on S.nation_id = N.id " +
                    "WHERE S.nation_id != ? " +
                    "ORDER BY S.id";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int nationId = rs.getInt("nation_id");
                String nation_name = rs.getString("nation_name");
                String nation_description = rs.getString("nation_description");
                String favored_trait = rs.getString("favored_trait");

                int schoolId = rs.getInt("school_id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                Nation nation = new Nation(nationId, nation_name, favored_trait, nation_description);
                SwordsmanSchool school = new SwordsmanSchool(schoolId, name, nation, description);


                otherSwordSchools.add(school);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otherSwordSchools;
    }

    /**
     * Returns the description of a school with a selected name
     *
     * @param schoolName the name of the school to return a description for
     * @return String description of the school
     */
    public static String getSchoolDescByName(String schoolName) {
        String description = null;
        try {
            String sql = "SELECT S.description " +
                    "FROM swordsman_schools S " +
                    "WHERE S.name = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, schoolName);
            ResultSet rs = ps.executeQuery();
            description = rs.getString("description");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return description;
    }

    /**
     * Returns a Swordsman School object of a nation with a selected id
     *
     * @param id id of the nation of school to return
     * @return SwordsmanSchool to return of the selected nation id
     */
    public static SwordsmanSchool getSwordSchoolById(int id) {
        SwordsmanSchool requestedSchool = null;
        Nation nation;
        try {
            String sql = "SELECT S.id, S.nation_id, S.name, S.description " +
                    "FROM swordsman_schools S " +
                    "WHERE S.nation_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int schoolId = rs.getInt("id");
                int nationId = rs.getInt("nation_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                nation = DBNation.getNationById(nationId);
                requestedSchool = new SwordsmanSchool(schoolId, name, nation, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requestedSchool;
    }
}
