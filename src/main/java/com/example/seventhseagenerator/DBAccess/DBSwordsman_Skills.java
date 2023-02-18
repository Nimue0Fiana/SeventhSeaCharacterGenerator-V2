package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBSwordsman_Skills {
    /**
     * Creates swordsman skill entries in the database
     * For use in the DBCreate Class
     */
    //Format:
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    public static void createSwordsmanSkills() {
        try {
            String sql = "INSERT INTO 'swordsman_skills' " +
                    "SELECT '1' AS 'id', '4' AS 'school_id', 'Fencing' AS 'name', 'Heroes trained in Fencing have a " +
                    "basic understanding of the theories and techniques of modern swordplay. This skill trains the Hero " +
                    "in the use of fencing weapons such as the rapier, foil, smallsword, and epee. It is the foundation " +
                    "for most of the training presented in Theahs Swordsman Schools.' AS 'description' " +
                    "UNION ALL SELECT '2', '4', 'Dirty Fighting', 'The niceties of pugilism are not for everyone. Some " +
                    "folks think that fair play is a good way to get killed.' " +
                    "UNION ALL SELECT '3', '2', 'Courtier', 'Courtiers are skilled in the diplomatic arts. As a member " +
                    "of this esteemed profession you can dine with kings, chat pleasantly with Cardinals or, when " +
                    "required, pull state secrets from your own lovers lips.' " +
                    "UNION ALL SELECT '4', '2', 'Fencing', 'Heroes trained in Fencing have a basic understanding of the " +
                    "theories and techniques of modern swordplay. This skill trains the Hero in the use of fencing " +
                    "weapons such as the rapier, foil, smallsword, and epee. It is the foundation for most of the " +
                    "training presented in Theahs Swordsman Schools.' " +
                    "UNION ALL SELECT '5', '1', 'Fencing', 'Heroes trained in Fencing have a basic understanding of the " +
                    "theories and techniques of modern swordplay. This skill trains the Hero in the use of fencing " +
                    "weapons such as the rapier, foil, smallsword, and epee. It is the foundation for most of the " +
                    "training presented in Theahs Swordsman Schools.' " +
                    "UNION ALL SELECT '6', '1', 'Buckler', 'Anyone can hold up a buckler, but a soldier trained in its " +
                    "use can turn it into an offensive weapon as well.' " +
                    "UNION ALL SELECT '7', '3', 'Fencing', 'Heroes trained in Fencing have a basic understanding of the " +
                    "theories and techniques of modern swordplay. This skill trains the Hero in the use of fencing " +
                    "weapons such as the rapier, foil, smallsword, and epee. It is the foundation for most of the " +
                    "training presented in Theahs Swordsman Schools.' " +
                    "UNION ALL SELECT '8', '3', 'Knife', 'Knives are more easily concealed than swords, but are commonly " +
                    "perceived as less of a threat. Sailors are often very proficient with knives, since they use them " +
                    "frequently in their daily tasks' ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
