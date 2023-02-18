package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBChar_Sorcery_Degree {
    /**
     * adds entry to char_sorcery_degree with id of character taking sorcery degree and the id of the sorcery degree taken
     *
     * @param lastPcId          id of character taking sorcery degree
     * @param sorcery_degree_id id of sorcery degree taken by character
     * @return rows affected
     */
    public static int addCharSorceryDegree(int lastPcId, int sorcery_degree_id) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO char_sorcery_degrees (character_id, sorcery_degree_id) " +
                    "VALUES (?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, lastPcId);
            ps.setInt(2, sorcery_degree_id);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
