package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBChar_Advantages {
    /**
     * adds entry to char_advantages with id of character taking advantage and the id of the advantage taken
     *
     * @param lastPcId    the id of the player character to add the advantage to
     * @param advantageId the id of the advantage to attach to this player character
     * @return rows affected
     */
    public static int addCharAdvantages(int lastPcId, int advantageId) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO char_advantages (character_id, advantage_id) " +
                    "VALUES (?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, lastPcId);
            ps.setInt(2, advantageId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
