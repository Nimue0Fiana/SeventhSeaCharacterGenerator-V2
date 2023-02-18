package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBChar_Skills {
    /**
     * adds entry to char_skills with id of character taking skill and the id of the skill taken
     *
     * @param lastPcId id of character taking skill
     * @param skillId  id of skill taken
     * @return rows affected in DB
     */
    public static int addCharSkills(int lastPcId, int skillId) {
        int rowsAffected = 0;
        try {
            String sql = "INSERT INTO char_skills (character_id, skill_id) " +
                    "VALUES (?, ?)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, lastPcId);
            ps.setInt(2, skillId);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
