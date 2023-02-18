package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBChar_Sorcery_Knacks {
    /**
     * adds entry to char_knacks with id of character taking knack and the id of the knack taken
     *
     * @param lastPcId         id of character taking advantage
     * @param sorcery_knack_id id of knack assigned to character
     * @param rank             rank the character bought of knack
     * @return rows affected
     */
    public static int addCharSorceryKnacks(int lastPcId, int sorcery_knack_id, int rank) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO char_sorcery_knacks (character_id, sorcery_knack_id, rank_level) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, lastPcId);
            ps.setInt(2, sorcery_knack_id);
            ps.setInt(3, rank);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

}
