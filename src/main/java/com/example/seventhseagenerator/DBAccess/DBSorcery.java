package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.Sorcery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSorcery {
    /**
     * Creates all sorceries in the database
     * For user in DBCreate Class
     */
//Format:
//INSERT INTO 'tablename'
//          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
//UNION ALL SELECT 'data1', 'data2'
//UNION ALL SELECT 'data1', 'data2'
//UNION ALL SELECT 'data1', 'data2'
    public static void createSorceries() {
        try {
            String sql = "INSERT INTO 'sorceries'" +
                    "SELECT '1' AS 'id', '1' AS 'nation_id', 'Glamour' AS 'name', 'In the land of Avalon, the legends of the " +
                    "people come to life. Avalon sorcerers harness the energy of the peoples belief and channel it in order " +
                    "to perform miraculous feats. This ability, known as glamour, was taught to the Avalons by the Sidhe, " +
                    "who gave them a magical artifact known as the Grall. If the Grall were to be lost, the sorcerers would " +
                    "lose their power until it was returned.' " +
                    "UNION ALL SELECT '2' AS 'id', '3' AS 'nation_id', 'Porte' AS 'name', 'The most famous sorcery. Doorway " +
                    "magic involves the sorcerer ripping holes in the fabric of the universe and stepping through to " +
                    "somewhere else.' AS 'description' " +
                    "UNION ALL SELECT '3', '4','Sorte', 'Fate magic is one of the rarest sorceries in Theah. It is only " +
                    "found in Vodacce, and runs only in the blood of its women. Those attuned to Sorte can see the great " +
                    "web of fate and how its tendrils connect to all things. With enough skill, the Sorte Strega can " +
                    "recognize the types of strands as well. Finally, the frand witches can create or destroy strands, a " +
                    "very dangerous undertaking.'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets sorcery by id
     *
     * @param id id of sorcery to return
     * @return Sorcery with id matching input
     */
    public static Sorcery getSorceryById(int id) {
        Sorcery sorcery = null;
        try {
            String sql = "SELECT S.id, S.name, S.description " +
                    "FROM sorceries S " +
                    "WHERE S.id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int sorceryId = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                sorcery = new Sorcery(sorceryId, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sorcery;
    }

    /**
     * Returns a sorcery for nation with given id
     *
     * @param id the id of the nation having the desired sorcery
     * @return Sorcery of the nation having the selected id
     */
    public static Sorcery getSorceryByNationId(int id) {
        Sorcery sorcery = null;
        try {
            String sql = "SELECT S.id, S.name, S.description " +
                    "FROM sorceries S " +
                    "WHERE S.nation_id = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int sorceryId = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                sorcery = new Sorcery(sorceryId, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sorcery;
    }
}
