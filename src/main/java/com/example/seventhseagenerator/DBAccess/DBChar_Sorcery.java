package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBChar_Sorcery {
    /**
     * adds entry to char_sorcery with id of character taking sorcery and the id of the sorcery taken
     *
     * @param lastPcId  id of character taking sorcery
     * @param sorceryId id of sorcery taken by character
     * @param blood     type of blood - full, half or double
     * @return rows affected
     */
    public static int addCharSorcery(int lastPcId, int sorceryId, int blood) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO char_sorcery (character_id, sorcery_id, blood) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, lastPcId);
            ps.setInt(2, sorceryId);
            ps.setInt(3, blood);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

}
