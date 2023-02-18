package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBChar_Sword_Degrees {
    /**
     * adds entry to char_sword_degree with id of character taking sword school degree and the id of the
     * sword school degree taken
     *
     * @param lastPcId      id of character taking sword degree
     * @param swordDegreeId id of sword degree taken by character
     * @return rows affected
     */
    public static int addCharSwordDegree(int lastPcId, int swordDegreeId) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO char_swordsman_degrees (character_id, swordsman_degree_id) " +
                    "VALUES (?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, lastPcId);
            ps.setInt(2, swordDegreeId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
