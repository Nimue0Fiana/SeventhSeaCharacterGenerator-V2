package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.Knack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBKnack {

    /**
     * Creates knack entries in database
     * For use by DBCreate class
     */
    //Format:
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    public static void createKnacks() {
        try {
            String sql = "INSERT INTO 'knacks' " +
                    "SELECT '1' AS 'id', '1' AS 'skill_id', 'Dancing' AS 'name', null AS 'knack_level', " +
                    "'0' AS 'isAdvanced', 'You ease your way around  a dance floor with grace and poise. Ballroom " +
                    "dancers with great skill are in high demand at noble balls, while ballet dancers can earn a decent " +
                    "living performing at stage shows and paid recitals.' AS 'description' " +
                    "UNION ALL SELECT '2', '1', 'Etiquette', null, '0', 'You have learned the niceties of of events " +
                    "sponsored by nobility and can avoid a faux pas. When you are using this Knack in a place whose " +
                    "customs you are unfamiliar with, you are at a penalty of -2 unkept dice.' " +
                    "UNION ALL SELECT '3', '1', 'Fashion', null, '0', 'With enough knowledge, you can throw together a " +
                    "presentable outfit, even if the materials at hand are less than satisfactory.' " +
                    "UNION ALL SELECT '4', '1', 'Oratory', null, '0', 'Sweet words of praise flow as easily from your " +
                    "lips as whispered words of poison. Oratory allows you to persuade your listeners more easily of " +
                    "any argument.' " +
                    "UNION ALL SELECT '5', '1', 'Diplomacy', null, '1', 'The art of diplomacy is the art of peace; " +
                    "words have prevented more wars than guns ever caused. Your soothing reassurances can calm all " +
                    "but the most enraged duelist, and keep you blood where it belongs - in your veins.' " +
                    "UNION ALL SELECT '6', '1', 'Gossip', null, '1', 'A rumor flies faster than the swiftest arrow and " +
                    "bites twice as deep. Gossips are always near the rumor mill and get the latest news before anyone " +
                    "else, but with this Knack, you also have the ability to determine which are true and which are important.' " +
                    "UNION ALL SELECT '7', '1', 'Politics', null, '1', 'Politics is power, and the truly clever " +
                    "courtier understands this. You can sense the ebb and flow of a nobles influence and determine " +
                    "whose coattails represent the quickest ride to the top.' " +
                    "UNION ALL SELECT '8', '1', 'Seduction', null, '1', 'The breathy whisper of a suptry woman has " +
                    "thrown more than one empire into chaos. Armed with this Knack, you have the potential to be more " +
                    "dangerous to the stability of a nation than a hundred soldiers.' " +
                    "UNION ALL SELECT '9', '2', 'Acting', null, '0', 'Slipping in and our of character is your forte. " +
                    "You can pretend to be a member of a different social class, exhibiting the humility of a beggar or " +
                    "the arrogance of a king. Of course, this is much more effective when combined with the proper custume.' " +
                    "UNION ALL SELECT '10', '2', 'Dancing', null, '0', 'You ease your way around  a dance floor with " +
                    "grace and poise. Ballroom dancers with great skill are in high demand at noble balls, while ballet " +
                    "dancers can earn a decent living performing at stage shows and paid recitals.' " +
                    "UNION ALL SELECT '11', '2', 'Oratory', null, '0', 'Sweet words of praise flow as easily from your " +
                    "lips as whispered words of poison. Oratory allows you to persuade your listeners more easily of " +
                    "any argument.' " +
                    "UNION ALL SELECT '12', '2', 'Singing', null, '0', 'A voice as clear as crystal is only part of your " +
                    "secret. Breath control and enunciation count for just as much. This Knack gives you the training " +
                    "to use your voice to the fullest' " +
                    "UNION ALL SELECT '13', '2', 'Animal Training', null, '1', 'This Knack allows you to domesticate " +
                    "animals and train them to perform tricks, or to attack on command.' " +
                    "UNION ALL SELECT '14', '2', 'Storytelling', null, '1', 'Seated around a flickering fire, all " +
                    "attention is on you. Your voice and mannerisms are calculated to enthrall your audience completely. " +
                    "Storytellers can, in addition, sometimes collect small sums of money for telling their tales.' " +
                    "UNION ALL SELECT '15', '3', 'Attack (Dirty Fighting)', null, '0', 'Attack is simply the ability to " +
                    "hit your enemy, and should be used in all Skill uses not covered by any other mechanic. Remember, " +
                    "a bare-handed attacks Damage Rating is 0k1.' " +
                    "UNION ALL SELECT '16', '3', 'Attack (Improvised Weapon)', null, '1', 'Attack is simply the ability " +
                    "to hit your enemy. This Knack is used when wielding a weapon that does not conform to any " +
                    "established Knack (like a table, a chest, or another human).' " +
                    "UNION ALL SELECT '17', '3', 'Kick', null, '1', 'A kick inflicts 0k2 damage, but raises the TN that " +
                    "you are trying to hit by 10. You must declare a Kick before rolling the attack and use this Knack " +
                    "instead of your normal Attack Knack.' " +
                    "UNION ALL SELECT '18', '4', 'Attack (Fencing)', null, '0', 'Attack is simply to hit your enemy.' " +
                    "UNION ALL SELECT '19', '4', 'Parry (Fencing)', null, '0', 'Parrying is the act of putting your " +
                    "sword between yourself and your enemies strikes. This Knack can be used as your Defense Knack " +
                    "while you are wielding a sword.' ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gives a concatenated String listing all knacks associated with a skill
     *
     * @param skillId id of skill to find knacks for
     * @return knackList String, comma separated
     */
    public static String allKnacksForSkill(int skillId) {
        String knackList = null;
        try {
            String sql = "SELECT GROUP_CONCAT(K.name, ', ') AS names " +
                    "FROM knacks K " +
                    "JOIN skills S on K.skill_id = S.id " +
                    "WHERE S.id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, skillId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                knackList = rs.getString("names");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knackList;
    }

    /**
     * Returns Observable List of knacks for a selected skill id
     *
     * @param skillId the skill id to return a list for
     * @return Observable List containing all knacks associated with the entered skill id
     */
    public static ObservableList knackListForSkill(int skillId) {
        ObservableList<Knack> associatedKnacks = FXCollections.observableArrayList();
        try {
            String sql = "SELECT K.id, K.skill_id, K.name, K.knack_level,  K.isAdvanced, K.description " +
                    "FROM knacks K " +
                    "WHERE K.skill_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, skillId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int skill_id = rs.getInt("skill_id");
                String name = rs.getString("name");
                int knack_level = rs.getInt("knack_level");
                boolean isAdvanced = rs.getBoolean("isAdvanced");
                String description = rs.getString("description");

                Knack knack = new Knack(id, skill_id, name, knack_level, isAdvanced, description);
                associatedKnacks.add(knack);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return associatedKnacks;
    }
}
