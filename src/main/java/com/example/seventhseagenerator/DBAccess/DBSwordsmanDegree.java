package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.SwordsmanDegree;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSwordsmanDegree {

    /**
     * Creates database entries for swordsman degrees
     * For use in the DBCreate class
     */
    //Format:
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    public static void createSwordsmanDegrees() {
        try {
            String sql = "INSERT INTO 'swordsman_degrees' " +
                    "SELECT '1' AS 'id', '2' AS 'swordsman_school_id', '1' AS 'degree', 'Apprentice (Aldana)' AS 'name', " +
                    "'The Apprentice is renowned for his lightning-fast reflexes and aggressive fighting style. " +
                    "You may roll one unkept die for Initiative for each Mastery level you have in Aldana.' AS 'description' " +
                    "UNION ALL SELECT '2', '4', '1', 'Apprentice (Ambrogia)', 'The Ambrogia style of fighting negates " +
                    "the off-hand penalty when using a dagger or main gauche, and gives the character the Left-Handed " +
                    "Advantage for free (but only while using this style of fighting). Apprentices among Veronicas " +
                    "Boys are also trained to think quickly and take advantage of every opportunity they get. You may " +
                    "twist you dagger or fencing weapon slightly when you hit, which automatically adds 2 to the damage " +
                    "you inflict (increasing a Damage Roll of 18 to 20 for example).' " +
                    "UNION ALL SELECT '3', '1', '1', 'Apprentice (Donovan)', 'Learning the Donovan style negates the " +
                    "offhand penalty when using a buckler, and grants one free Raise when using the buckler.' " +
                    "UNION ALL SELECT '4', '3', '1', 'Apprentice (Valroux)', 'Learning the Valroux style of fighting " +
                    "negates the off-hand penalty when using a dagger or main gauche, and grants a free Raise when " +
                    "parrying with one of those weapons in your off-hand.' ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns a Swordsman Degree from a school with an id matching the input int
     *
     * @param schoolId id of school to return a degree for
     * @return SwordsmanDegree from a swordsman school with matching id
     */
    public static SwordsmanDegree getSwordsmanDegreeBySchoolId(int schoolId) {
        SwordsmanDegree requestedDegree = null;
        try {
            String sql = "SELECT D.id, D.swordsman_school_id, D.name, D.description " +
                    "FROM swordsman_degrees D " +
                    "WHERE swordsman_school_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, schoolId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int school_id = rs.getInt("swordsman_school_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                requestedDegree = new SwordsmanDegree(id, school_id, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestedDegree;

    }
}
