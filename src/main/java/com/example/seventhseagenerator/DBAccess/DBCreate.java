package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBCreate {

    /**
     * Creates all database tables and enters all data in the database.
     * Should execute only when the user has no database created in their appdata folder.
     */
    public static void createTables() {
        try {
            String sql = "CREATE TABLE 'advantages' ('id' INTEGER NOT NULL, 'name' TEXT NOT NULL, 'description' TEXT NOT NULL, " +
                    "'point_cost' INTEGER NOT NULL, PRIMARY KEY ('id'))";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();

            String sql2 = "CREATE TABLE 'characters' ('id' INTEGER NOT NULL,'name' TEXT NOT NULL, 'player' TEXT NOT NULL, " +
                    "'hero_points' INTEGER NOT NULL, 'gender' TEXT NOT NULL, 'nation_id' INTEGER NOT NULL, 'brawn' INTEGER NOT NULL, " +
                    "'finesse' INTEGER NOT NULL, 'wits' INTEGER NOT NULL, 'resolve' INTEGER NOT NULL, 'panache' INTEGER NOT NULL, " +
                    "'created' TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY('nation_id') REFERENCES 'nations'('id'), " +
                    "PRIMARY KEY('id'))";
            PreparedStatement ps2 = JDBC.getConnection().prepareStatement(sql2);
            ps2.executeUpdate();

            String sql3 = "CREATE TABLE \"char_advantages\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"advantage_id\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"advantage_id\") REFERENCES \"advantages\"(\"id\")\n" +
                    ")";
            PreparedStatement ps3 = JDBC.getConnection().prepareStatement(sql3);
            ps3.executeUpdate();

            String sql4 = "CREATE TABLE \"char_knacks\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"knack_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"rank_level\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"knack_id\") REFERENCES \"knacks\"(\"id\")\n" +
                    ")";
            PreparedStatement ps4 = JDBC.getConnection().prepareStatement(sql4);
            ps4.executeUpdate();

            String sql5 = "CREATE TABLE \"char_skills\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"skill_id\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"skill_id\") REFERENCES \"skills\"(\"id\")\n" +
                    ")";
            PreparedStatement ps5 = JDBC.getConnection().prepareStatement(sql5);
            ps5.executeUpdate();

            String sql6 = "CREATE TABLE \"char_sorcery\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"sorcery_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"blood\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"sorcery_id\") REFERENCES \"sorceries\"(\"id\")\n" +
                    ")";
            PreparedStatement ps6 = JDBC.getConnection().prepareStatement(sql6);
            ps6.executeUpdate();

            String sql7 = "CREATE TABLE \"char_sorcery_degrees\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"sorcery_degree_id\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"sorcery_degree_id\") REFERENCES \"sorcery_degrees\"(\"id\")\n" +
                    ")";
            PreparedStatement ps7 = JDBC.getConnection().prepareStatement(sql7);
            ps7.executeUpdate();

            String sql8 = "CREATE TABLE \"char_sorcery_knacks\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"sorcery_knack_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"rank_level\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"sorcery_knack_id\") REFERENCES \"sorcery_knacks\"(\"id\")\n" +
                    ")";
            PreparedStatement ps8 = JDBC.getConnection().prepareStatement(sql8);
            ps8.executeUpdate();

            String sql9 = "CREATE TABLE \"char_sword_knacks\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"swordsman_knack_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"rank_level\"\tINTEGER,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"swordsman_knack_id\") REFERENCES \"swordsman_knacks\"(\"id\")\n" +
                    ")";
            PreparedStatement ps9 = JDBC.getConnection().prepareStatement(sql9);
            ps9.executeUpdate();

            String sql10 = "CREATE TABLE \"char_sword_school\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"swordsman_school_id\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"swordsman_school_id\") REFERENCES \"swordsman_schools\"(\"id\")\n" +
                    ")";
            PreparedStatement ps10 = JDBC.getConnection().prepareStatement(sql10);
            ps10.executeUpdate();

            String sql11 = "CREATE TABLE \"char_swordsman_degrees\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"swordsman_degree_id\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"swordsman_degree_id\") REFERENCES \"swordsman_degrees\"(\"id\")\n" +
                    ")";
            PreparedStatement ps11 = JDBC.getConnection().prepareStatement(sql11);
            ps11.executeUpdate();

            String sql12 = "CREATE TABLE \"knacks\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"skill_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"name\"\tTEXT NOT NULL,\n" +
                    "\t\"knack_level\"\tINTEGER,\n" +
                    "\t\"isAdvanced\"\tINTEGER NOT NULL,\n" +
                    "\t\"description\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"skill_id\") REFERENCES \"skills\"(\"id\"),\n" +
                    "\tPRIMARY KEY(\"id\")\n" +
                    ")";
            PreparedStatement ps12 = JDBC.getConnection().prepareStatement(sql12);
            ps12.executeUpdate();

            String sql13 = "CREATE TABLE \"nations\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"name\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\t\"favored_trait\"\tTEXT NOT NULL,\n" +
                    "\t\"description\"\tTEXT NOT NULL,\n" +
                    "\t\"sorcery_id\"\tINTEGER,\n" +
                    "\tPRIMARY KEY(\"id\"),\n" +
                    "\tFOREIGN KEY(\"sorcery_id\") REFERENCES \"sorceries\"(\"id\")\n" +
                    ")";
            PreparedStatement ps13 = JDBC.getConnection().prepareStatement(sql13);
            ps13.executeUpdate();

            String sql14 = "CREATE TABLE \"skills\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"name\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\t\"description\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\tPRIMARY KEY(\"id\")\n" +
                    ")";
            PreparedStatement ps14 = JDBC.getConnection().prepareStatement(sql14);
            ps14.executeUpdate();

            String sql15 = "CREATE TABLE \"sorceries\" (\n" +
                    "\t\"id\"\tINTEGER,\n" +
                    "\t\"nation_id\"\tINTEGER NOT NULL UNIQUE,\n" +
                    "\t\"name\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\t\"description\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\tFOREIGN KEY(\"nation_id\") REFERENCES \"nations\"(\"id\"),\n" +
                    "\tPRIMARY KEY(\"id\")\n" +
                    ")";
            PreparedStatement ps15 = JDBC.getConnection().prepareStatement(sql15);
            ps15.executeUpdate();

            String sql16 = "CREATE TABLE \"sorcery_blood\" (\n" +
                    "\t\"blood_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"blood_name\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\tPRIMARY KEY(\"blood_id\")\n" +
                    ")";
            PreparedStatement ps16 = JDBC.getConnection().prepareStatement(sql16);
            ps16.executeUpdate();

            String sql17 = "CREATE TABLE \"sorcery_degrees\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"sorcery_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"degree\"\tINTEGER NOT NULL,\n" +
                    "\t\"name\"\tTEXT NOT NULL,\n" +
                    "\t\"description\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\tFOREIGN KEY(\"sorcery_id\") REFERENCES \"sorceries\"(\"id\"),\n" +
                    "\tPRIMARY KEY(\"id\")\n" +
                    ")";
            PreparedStatement ps17 = JDBC.getConnection().prepareStatement(sql17);
            ps17.executeUpdate();

            String sql18 = "CREATE TABLE \"sorcery_knacks\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"sorcery_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"sorcery_degree_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"name\"\tTEXT NOT NULL,\n" +
                    "\t\"description\"\tTEXT NOT NULL,\n" +
                    "\tFOREIGN KEY(\"sorcery_degree_id\") REFERENCES \"sorcery_degrees\"(\"id\"),\n" +
                    "\tFOREIGN KEY(\"sorcery_id\") REFERENCES \"sorceries\"(\"id\"),\n" +
                    "\tPRIMARY KEY(\"id\")\n" +
                    ")";
            PreparedStatement ps18 = JDBC.getConnection().prepareStatement(sql18);
            ps18.executeUpdate();

            String sql19 = "CREATE TABLE \"swordsman_degrees\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"swordsman_school_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"degree\"\tINTEGER NOT NULL,\n" +
                    "\t\"name\"\tTEXT NOT NULL,\n" +
                    "\t\"description\"\tTEXT NOT NULL,\n" +
                    "\tPRIMARY KEY(\"id\"),\n" +
                    "\tFOREIGN KEY(\"swordsman_school_id\") REFERENCES \"swordsman_schools\"(\"id\")\n" +
                    ")";
            PreparedStatement ps19 = JDBC.getConnection().prepareStatement(sql19);
            ps19.executeUpdate();

            String sql20 = "CREATE TABLE \"swordsman_knacks\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"skill_id\"\tINTEGER,\n" +
                    "\t\"school_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"name\"\tTEXT NOT NULL,\n" +
                    "\t\"description\"\tTEXT NOT NULL,\n" +
                    "\tFOREIGN KEY(\"school_id\") REFERENCES \"swordsman_schools\"(\"id\"),\n" +
                    "\tPRIMARY KEY(\"id\")\n" +
                    ")";
            PreparedStatement ps20 = JDBC.getConnection().prepareStatement(sql20);
            ps20.executeUpdate();

            String sql21 = "CREATE TABLE \"swordsman_schools\" (\n" +
                    "\t\"id\"\tINTEGER NOT NULL,\n" +
                    "\t\"nation_id\"\tINTEGER NOT NULL UNIQUE,\n" +
                    "\t\"name\"\tTEXT NOT NULL UNIQUE,\n" +
                    "\t\"description\"\tINTEGER NOT NULL UNIQUE,\n" +
                    "\tFOREIGN KEY(\"nation_id\") REFERENCES \"nations\"(\"id\"),\n" +
                    "\tPRIMARY KEY(\"id\")\n" +
                    ")";
            PreparedStatement ps21 = JDBC.getConnection().prepareStatement(sql21);
            ps21.executeUpdate();

            String sql22 = "CREATE TABLE \"swordsman_skills\" (\n" +
                    "\t\"id\"\tINTEGER,\n" +
                    "\t\"school_id\"\tINTEGER,\n" +
                    "\t\"name\"\tTEXT,\n" +
                    "\t\"description\"\tTEXT,\n" +
                    "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
                    ")";
            PreparedStatement ps22 = JDBC.getConnection().prepareStatement(sql22);
            ps22.executeUpdate();

            String sql23 = "CREATE TABLE \"char_equipment\" (\n" +
                    "\t\"character_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"equipment_id\"\tINTEGER NOT NULL,\n" +
                    "\t\"quantity\"\tINTEGER NOT NULL,\n" +
                    "\tFOREIGN KEY(\"character_id\") REFERENCES \"characters\"(\"id\") ON DELETE CASCADE,\n" +
                    "\tFOREIGN KEY(\"equipment_id\") REFERENCES \"equipment\"(\"id\")\n" +
                    ")";
            PreparedStatement ps23 = JDBC.getConnection().prepareStatement(sql23);
            ps23.executeUpdate();

            DBNation.createNations();
            DBSorcery.createSorceries();
            DBSorceryDegree.createSorceryDegrees();
            DBSorceryKnack.createSorceryKnacks();
            DBAdvantage.createAdvantages();
            DBSkill.createSkills();
            DBKnack.createKnacks();
            DBSwordsmanSchool.createSwordsmanSchools();
            DBSwordsmanDegree.createSwordsmanDegrees();
            DBSwordsman_Skills.createSwordsmanSkills();
            DBSwordsmanKnack.createSwordsmanKnacks();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//        try {
//                String sql = "";
//                PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
//                ps.executeUpdate();
//                }catch(SQLException e) {
//                e.printStackTrace();
//                }