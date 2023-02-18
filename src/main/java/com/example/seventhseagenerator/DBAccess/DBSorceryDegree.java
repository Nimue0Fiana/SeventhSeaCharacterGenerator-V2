package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.SorceryDegree;
import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.SorceryDegree;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSorceryDegree {
    /**
     * Creates entries for sorcery degrees in database
     * For user in the DBCreate Class
     */
    //Format:
//INSERT INTO 'tablename'
//          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
//UNION ALL SELECT 'data1', 'data2'
//UNION ALL SELECT 'data1', 'data2'
//UNION ALL SELECT 'data1', 'data2
    public static void createSorceryDegrees() {
        try {
            String sql = "INSERT INTO sorcery_degrees " +
                    "SELECT '1' AS 'id', '1' AS 'sorcery_id', '1' AS 'degree', 'Renown' AS 'name', 'At this level of " +
                    "mastery, you get two benefits. First, all your Reputation Dice are considered Glamour Dice. " +
                    "Second, you can use the Appretice ability of any Legend Knacks you know by spending one Drama Die' AS 'description' " +
                    "UNION ALL SELECT '2', '2', '1', 'Items', 'In the beginning of your training you learn to make " +
                    "small doorways, just large enough for a fist to fit through. Then, you are taught the bleeding " +
                    "method.  You mark an object such as a mirror, knife, or snuffbox with your own blood. Then you are " +
                    "taken to another room and told to concentrate on the object. When you are ready, you rip open a " +
                    "small hole and reach through to the blooded object. When you feel the object, you pull it to you " +
                    "back through the doorway.'" +
                    "UNION ALL SELECT '3', '3', '1', 'Touching the Strands', 'Sorte apprentices first learn to sense the " +
                    "strands of fate. As the apprentices skill grows, she can see the connections between people. Soon " +
                    "enough, she can also determine the nature of those connections. A Clotho, for instance, could see " +
                    "the Cups strand between two secret lovers, and the Swords strand beginning to form between her " +
                    "lover and her unaware husband.'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns Sorcery Degree based on entered sorcery id
     *
     * @param sorcery_id the id of the sorcery having the needed id
     * @return Sorcery id from the sorcery with a matching id.
     */
    public static SorceryDegree getSorceryDegreeBySorcery(int sorcery_id) {

        SorceryDegree requestedDegree = null;
        try {
            String sql = "SELECT D.id, D.sorcery_id,D.degree, D.name, D.description " +
                    "FROM sorcery_degrees D " +
                    "WHERE sorcery_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, sorcery_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int school_id = rs.getInt("sorcery_id");
                int degree = rs.getInt("degree");
                String name = rs.getString("name");
                String description = rs.getString("description");
                requestedDegree = new SorceryDegree(id, sorcery_id, degree, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestedDegree;


    }
}
