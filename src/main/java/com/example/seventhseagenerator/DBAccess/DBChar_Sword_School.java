package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBChar_Sword_School {
    /**
     * adds entry to char_sword_school with id of character taking sword school and the id of the sword school
     *
     * @param lastPcId      id of character taking sword school
     * @param swordSchoolId id of sword school taken by character
     * @return rows affected
     */
    public static int addCharSwordSchool(int lastPcId, int swordSchoolId) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO char_sword_school (character_id, swordsman_school_id) " +
                    "VALUES (?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, lastPcId);
            ps.setInt(2, swordSchoolId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
