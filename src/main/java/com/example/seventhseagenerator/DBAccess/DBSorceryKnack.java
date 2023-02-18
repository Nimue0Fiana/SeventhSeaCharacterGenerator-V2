package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.SorceryKnack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSorceryKnack {

    /**
     * Creates sorcery knack entries in database
     * For use in the DBCreate Class
     */
    //Format:
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2
    public static void createSorceryKnacks() {
        try {
            String sql = "INSERT INTO 'sorcery_knacks' " +
                    "SELECT '1' AS 'id', '1' AS 'sorcery_id', '1' AS 'sorcery_degree_id', 'The Horned Hunter (Brawn)' " +
                    "AS 'name', 'Spend a Drama Die to add your Rank in this Knack to your Brawn for one roll. This " +
                    "cannot be used during Contested Rolls, Wound Checks, or Damage Rolls.' AS 'description' " +
                    "UNION ALL SELECT '2', '1', '1', 'Robin Goodfellow (Finesse)', 'Spend a Drama Die to lower the " +
                    "effective Range of your next attack with a  how by 5 feet for every Tank you have in this Knack.' " +
                    "UNION ALL SELECT '3', '1', '1', 'Jack (Wits)', 'You may spend a Drama die to transform a small " +
                    "object (less than one cubic foot in size) into one of the following items until the next dawn " +
                    "(or until you will the enchantment to end), when it reverts to its former self. If a transformed " +
                    "object is broken into pieces, all of the pieces disappear at dawn except for one, which reverts to " +
                    "its original, unharmed form... A knife, a hunk of cheese, a bird, a rock, a pair of dice, a " +
                    "playing or sorte card, Guilder, a 20-foot ball of twine, a button.' " +
                    "UNION ALL SELECT '4', '1', '1', 'The Green Man (Resolve)', 'Spend a Drama die, then toll one " +
                    "non-exploding die for every Tank you have in this Knack and Keep the highest one. Give that die to " +
                    "another Hero, who gaims a bonus to all his rolls equal to the number showing on the die until the " +
                    "end of the Scene. When the effect wears off at the end of the Scene, the Hero you gave the die to " +
                    "suffers one Dramatic Wound for every 5 points you rolled on the die, rounded up.' " +
                    "UNION ALL SELECT '5', '1', '1', 'Thomas (Panache)', 'Whenever someone or something that possesses " +
                    "sorcery comeswithing thirty feet of you, your left thumb begins to tingle until he or she moves " +
                    "out of range. When someone uses sorcery directly on you, you immediately detect whats being done, " +
                    "and if you spend a Drama Die you may resist that magic. The Rank of the Knack affecting you must " +
                    "be less than or equal to your Rank in this Knack.' " +
                    "UNION ALL SELECT '6', '2', '1', 'Attunement', 'Attunement allows a hero to sense, very generally, " +
                    "where the items he has blooded are in relation to himself. For every Rank in this Knack, you can " +
                    "sense items that are further away. Rank 1: 10 Feet  Rank 2: 100 feet  Rank 3: 1 mile  Rank 4: " +
                    "5 miles  Rank 5: 10 miles' " +
                    "UNION ALL SELECT '7', '2', '1', 'Blooding', 'When a Hero wishes to blood an object, he makes a " +
                    "Resolve + Blooding roll against a TN of 20. Success means that the item is blooded and the Hero " +
                    "can home in on it and open portals to its location.' " +
                    "UNION ALL SELECT '8', '2', '1', 'Bring', 'When a Hero wants to Brind a blooded item to himself, he " +
                    "rolls Resolve + Bring. The case TN is 20, reduced by 5 for every Raise made when the object was " +
                    "first blooded. It requires one Action to open a small doorway and one Action to reach through and " +
                    "pull the object out.' " +
                    "UNION ALL SELECT '9', '2', '1', 'Pocket', 'The Hero can claim a small pocket of the Walkway as his " +
                    "own and store things there. These objects do not need to be blooded, but there are a few restrictions. " +
                    "1.) Living creatures cannot be stored. 2.) The sorcerer can only store up to ten pounds of items. " +
                    "3.) There is a small chance the items may disappear when placed in the pocket.' " +
                    "UNION ALL SELECT '10', '2', '1', 'Walk', 'When a Hero wants to Walk to a blooded item from wherever " +
                    "he may be, he rolls Resolve + walk. The base TN is 20, but this is reduced by 5 for every Raise " +
                    "made when the object was first blooded.' " +
                    "UNION ALL SELECT '11', '3', '1', 'Arcana', 'When a Fate Witch wishes to see of a person has an " +
                    "Arcana, she must roll against a new TN of 15. if she succeeds, she can tell whether or not that " +
                    "person has an Arcana, and if so which one he or she possesses. In game terms, the Arcana reveals " +
                    "which Hubris or Virtue the Hero possesses, if any.' " +
                    "UNION ALL SELECT '12', '3', '1', 'Coins', 'Coins are yellow strands that represent Commerce. A " +
                    "Coin strand from one person to another indicates some sort of business relationship, or a " +
                    "relationship based on fiscal advantage.' " +
                    "UNION ALL SELECT '13', '3', '1', 'Cups', 'Cups are blue strands that represent Passion. A Cup " +
                    "strand indicates an emotional link between the two subjects.' " +
                    "UNION ALL SELECT '14', '3', '1', 'Swords', 'Swords are red strands that represent Conflict. A Sword " +
                    "strand indicates that some degree of conflict - emotional or physical - exists between two subjects.' " +
                    "UNION ALL SELECT '15', '3', '1', 'Staves', 'Stages are green strands that represent Authority. A " +
                    "Staff strand between two individuals represents a relationship basedon status or respect - forced " +
                    "or otherwise - rather than emotion or commerce.' ";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns list of all sorcery knacks associated with a sorcery
     *
     * @param sorcery_id the id of the sorcery to return knacks for
     * @return ObservableList<SorceryKnack> for all knacks matching the sorcery with id entered
     */
    public static ObservableList<SorceryKnack> getAllKnacksForSorcery(int sorcery_id) {
        ObservableList<SorceryKnack> knacksForSorcery = FXCollections.observableArrayList();
        try {
            String sql = "SELECT K.id, K.name, K.description " + "FROM sorcery_knacks K " + "WHERE K.sorcery_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, String.valueOf(sorcery_id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");

                SorceryKnack sorceryKnack = new SorceryKnack(id, name, description);
                knacksForSorcery.add(sorceryKnack);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knacksForSorcery;
    }
}
