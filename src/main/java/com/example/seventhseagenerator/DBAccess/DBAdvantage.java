package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.Advantages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAdvantage {
    /**
     * Creates advantages in the database
     */
    //Format:
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    public static void createAdvantages() {
        try {
            String sql = "INSERT INTO 'advantages' " +
                    "SELECT '1' AS 'id', 'Able Drinker' AS 'name', 'However much you drink, liquor never affects any of " +
                    "your rolls.' AS 'description', '1' AS 'point_cost' " +
                    "UNION ALL SELECT '2', 'Combat Reflexes', 'After initiative is rolled for any Combat Round, you may " +
                    "re-roll one of your action dice, but you are forced to keep the new roll.', '3' " +
                    "UNION ALL SELECT '3', 'Appearance - Above Average', '+1 unkept die for all social rolls', '5' " +
                    "UNION ALL SELECT '4', 'Appearance - Stunning', '+2 unkept dice for all social rolls', '10' " +
                    "UNION ALL SELECT '5','Dangerous Beauty', 'You always roll 2 extra unkept dice for any seduction attempts.', '3' ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a list of all advantages in the database
     *
     * @return
     */
    public static ObservableList<Advantages> getAllAdvantages() {
        ObservableList<Advantages> advantages = FXCollections.observableArrayList();
        try {
            String sql = "SELECT A.id, A.name, A.description, A.point_cost " +
                    "FROM advantages A";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int cost = rs.getInt("point_cost");

                Advantages advantage = new Advantages(id, name, cost, description);
                advantages.add(advantage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return advantages;
    }
}
