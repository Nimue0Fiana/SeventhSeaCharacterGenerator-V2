package com.example.seventhseagenerator.DBAccess;

import com.example.seventhseagenerator.Helper.JDBC;
import com.example.seventhseagenerator.Models.SwordsmanKnack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSwordsmanKnack {
    /**
     * Creates swordsman knacks in database
     * For use in the DBCreate Class
     */
    //Format:
    //INSERT INTO 'tablename'
    //          SELECT 'data1' AS 'column1', 'data2' AS 'column2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    //UNION ALL SELECT 'data1', 'data2'
    public static void createSwordsmanKnacks() {
        try {
            String sql = "INSERT INTO 'swordsman_knacks' " +
                    "SELECT '1' AS 'id', '6' AS 'skill_id', '1' AS 'school_id', 'Bind (Buckler)' AS 'name', " +
                    "'A Bind is only usable against an opponents fencing weapon. It locks your sword (or buckler, or " +
                    "panzerhand) and his sword together. To use this attack, you declare that you are attempting to " +
                    "Bind your opponents weapon, and then roll to attack using this Knack. If successful, you have " +
                    "momentarily bound his sword. While the two of you are in the Bind, neither of you can use your " +
                    "bound item.' AS 'description' " +
                    "UNION ALL SELECT '2', '5', '1', 'Disarm (Fencing)', 'You can only use this Knack after an opponent " +
                    "has just missed your Passive Defense. Spend an Action Die to make a Contested Roll of your Brawn + " +
                    "Disarm versus his Brawn + Attack (in the weapon he is wielding). If you win, the weapon is knocked " +
                    "out of his hand. If you perform this move with two Raises, you can wind up holding the weapon if you choose.' " +
                    "UNION ALL SELECT '3', '5', '1', 'Riposte (Fencing)', 'A riposte is a parry followed up immediately " +
                    "with a counterattack. You first attempt an Active Defense against the incoming Attack, and then, if " +
                    "the Active Defense is successful, make an Attack of your own on the person who just attacked you. " +
                    "When performing a Riposte, you receive one-half the dice from your Parry Knack (rounded down) for " +
                    "your Active Defense and one-half the dice from your Attack Knack (rounded down) for your counterattack. " +
                    "For every Rank in Riposte, you may add one die to either the Active Defense attempt or the " +
                    "counterattack. These dice are added after youve halved the appropriate Knacks.' " +
                    "UNION ALL SELECT '4', null, '1', 'Exploit Weakness (Donovan)', 'Once you have attended a School, " +
                    "you have not only learned its strengths, but its weaknesses as well. Whenever you duel against " +
                    "someone who is using a School whose weakness you are familiar with, even if you arent currently " +
                    "using that style of fighting yourself, you gain a number of unkept dice (+1k0 each) equal to your " +
                    "Rank in your Exploit Weakness Knack to all your Attack and Active Defense rolls. A Swordsman who " +
                    "attends more than one School obviously knows more than one weakness...' " +
                    "UNION ALL SELECT '5', '4', '2', 'Feint (Fencing)', 'When attacking the enemy, you can declare a " +
                    "Feint. You roll Finesse + Feint, but you must make a number of Raises equal to your enemys Wits. " +
                    "If you manage this, he cannot avoid the attack using any Active Defense.' " +
                    "UNION ALL SELECT '6', '4', '2', 'Riposte (Fencing)', 'A riposte is a parry followed up immediately " +
                    "with a counterattack. You first attempt an Active Defense against the incoming Attack, and then, " +
                    "if the Active Defense is successful, make an Attack of your own on the person who just attacked you. " +
                    "When performing a Riposte, you receive one-half the dice from your Parry Knack (rounded down) " +
                    "for your Active Defense and one-half the dice from your Attack Knack (rounded down) for your " +
                    "counterattack. For every Rank in Riposte, you may add one die to either the Active Defense attempt " +
                    "or the counterattack. These dice are added after youve halved the appropriate Knacks.' " +
                    "UNION ALL SELECT '7', '4', '2', 'Tagging (Fencing)', 'Tagging is a particularly flashy piece of " +
                    "showing off with your weapon, designed to temporarily dishearten your enemy. This could be anything " +
                    "from cutting off a lock of his hair to carving your initials in his shirt. You must use this Knack " +
                    "instead of your Attack Knack for your Attack Roll. If you successfully hit, the attack causes " +
                    "no damage, but you can cause one of two things to happen. Either your opponent loses 1 Drama Die " +
                    "until the end of the battle (at which point it returns), or you can gain 1 Drama Die yourself " +
                    "until the end of the battle (at which point it disappears if still unspent). These Drama Dice " +
                    "never become Experience Points, even if the battle was the last thing to happen in the Story.' " +
                    "UNION ALL SELECT '8', null, '2', 'Exploit Weakness (Aldana)', 'Once you have attended a School, " +
                    "you have not only learned its strengths, but its weaknesses as well. Whenever you duel against " +
                    "someone who is using a School whose weakness you are familiar with, even if you arent currently " +
                    "using that style of fighting yourself, you gain a number of unkept dice (+1k0 each) equal to your " +
                    "Rank in your Exploit Weakness Knack to all your Attack and Active Defense rolls. A Swordsman who " +
                    "attends more than one School obviously knows more than one weakness...' " +
                    "UNION ALL SELECT '9', '7', '3', 'Double-parry (Fencing/Knife)', 'A double-parry is parrying with " +
                    "two blades (usually a fencing weapon and a main gauche) crossed before you. You may declare that " +
                    "you are using this Knack instead of an ordinary Parry as an Active Defense. Success grants one " +
                    "Drama Die, which can be used for a number of Phases equal to your Rank with this maneuver. In any " +
                    "event, if you do not use the Drama Die by the end of the Round, you lose it.' " +
                    "UNION ALL SELECT '10', '7', '3', 'Feint (Fencing)', 'When attacking the enemy, you can declare a " +
                    "Feint. You roll Finesse + Feint, but you must make a number of Raises equal to your enemys Wits. " +
                    "If you manage this, he cannot avoid the attack using any Active Defense.' " +
                    "UNION ALL SELECT '11', '7', '3', 'Tagging (Fencing)', 'Tagging is a particularly flashy piece of " +
                    "showing off with your weapon, designed to temporarily dishearten your enemy. This could be anything " +
                    "from cutting off a lock of his hair to carving your initials in his shirt. You must use this Knack " +
                    "instead of your Attack Knack for your Attack Roll. If you successfully hit, the attack causes no " +
                    "damage, but you can cause one of two things to happen. Either your opponent loses 1 Drama Die " +
                    "until the end of the battle (at which point it returns), or you can gain 1 Drama Die yourself " +
                    "until the end of the battle (at which point it disappears if still unspent). These Drama Dice " +
                    "never become Experience Points, even if the battle was the last thing to happen in the Story.' " +
                    "UNION ALL SELECT '12', null, '3', 'Exploit Weakness (Valroux)', 'Once you have attended a School, " +
                    "you have not only learned its strengths, but its weaknesses as well. Whenever you duel against " +
                    "someone who is using a School whose weakness you are familiar with, even if you arent currently " +
                    "using that style of fighting yourself, you gain a number of unkept dice (+1k0 each) equal to your " +
                    "Rank in your Exploit Weakness Knack to all your Attack and Active Defense rolls. A Swordsman who " +
                    "attends more than one School obviously knows more than one weakness...' " +
                    "UNION ALL SELECT '13', '1', '4', 'Feint (Fencing)', 'When attacking the enemy, you can declare a " +
                    "Feint. You roll Finesse + Feint, but you must make a number of Raises equal to your enemys Wits. " +
                    "If you manage this, he cannot avoid the attack using any Active Defense.' " +
                    "UNION ALL SELECT '14', '1', '4', 'Pommel Strike (Fencing)', 'A pommel strike involves smashing the " +
                    "hilt of your sword into your targets face. You declare that you are using this Knack instead of the " +
                    "Attack Knack. If your attack gets through, you inflict a 0k2 attack, and your opponents TN to be " +
                    "hit is reduced to 5 until the end of the next Phase.' " +
                    "UNION ALL SELECT '15', '1', '4', 'Riposte (Fencing)', 'A riposte is a parry followed up immediately " +
                    "with a counterattack. You first attempt an Active Defense against the incoming Attack, and then, " +
                    "if the Active Defense is successful, make an Attack of your own on the person who just attacked you. " +
                    "When performing a Riposte, you receive one-half the dice from your Parry Knack (rounded down) for " +
                    "your Active Defense and one-half the dice from your Attack Knack (rounded down) for your counterattack. " +
                    "For every Rank in Riposte, you may add one die to either the Active Defense attempt or the " +
                    "counterattack. These dice are added after youve halved the appropriate Knacks.' " +
                    "UNION ALL SELECT '16', null, '4', 'Exploit Weakness (Ambrogia)', 'Once you have attended a School, " +
                    "you have not only learned its strengths, but its weaknesses as well. Whenever you duel against " +
                    "someone who is using a School whose weakness you are familiar with, even if you arent currently " +
                    "using that style of fighting yourself, you gain a number of unkept dice (+1k0 each) equal to your " +
                    "Rank in your Exploit Weakness Knack to all your Attack and Active Defense rolls. A Swordsman who " +
                    "attends more than one School obviously knows more than one weakness...' " +
                    "UNION ALL SELECT '17', '6', '1', 'Parry (Buckler)', 'Parrying is the act of putting your buckler " +
                    "between yourself and your enemies strikes. This Knack can be used as your Defense Knack while you " +
                    "are wearing a buckler.' " +
                    "UNION ALL SELECT '18', '5', '1', 'Attack (Fencing)', 'Attack is simply the ability to hit your enemy' " +
                    "UNION ALL SELECT '19', '5', '1', 'Parry (Fencing)', 'Parrying is the act of putting your sword " +
                    "between yourself and your enemies strikes. This Knack can be used as your Defense Knack while you " +
                    "are wielding a sword.' " +
                    "UNION ALL SELECT '20', '3', '2', 'Dancing (Courtier)', 'You ease your way around the dance floor " +
                    "with grace and poise. Ballroom dancers with great skill are in high demand at noble balls, while " +
                    "ballet dancers can earn a decent living performing at stage shows and paid recitals.' " +
                    "UNION ALL SELECT '21', '3', '2', 'Etiquette (Courtier)', 'While it might be acceptable to throw " +
                    "the bones of your meal upon the floor in some regions, it is certain that other nobility would " +
                    "faint upon seeing some ruffian throw a greasy bone on their new rug. You have learned the niceties " +
                    "of events sponsored by the nobility and can avoid such social faux pas. When you are using this " +
                    "Knack in a place whose customs you are unfamiliar with, you are at a penalty of -2 unkept dice.' " +
                    "UNION ALL SELECT '22', '3', '2', 'Fashion  (Courtier)', 'When appearing before nobility, it is an " +
                    "affront to their gentle senses to be ill-attired. The conscientious courtier keeps careful tabs on " +
                    "the latest trends in fashion, no matter how ridiculous. With enough knowledge, you can quickly throw " +
                    "together a presentable outfit, even if the materials at hand are less than satisfactory.' " +
                    "UNION ALL SELECT '23', '3', '2', 'Oratory (Courtier)', 'Sweet words of praise flow as easily from " +
                    "your lips as whispered words of poison. Oratory allows you to persuade your listeners more easily " +
                    "of any argument.' " +
                    "UNION ALL SELECT '24', '8', '3', 'Attack (Knife)', 'Attack is simply the ability to hit your enemy' " +
                    "UNION ALL SELECT '25', '8', '3', 'Parry (Knife)', 'Parrying is the act of putting your weapon " +
                    "between yourself and your enemies strikes. This Knack can be used as your Defense Knack while " +
                    "you are wielding a knife.' " +
                    "UNION ALL SELECT '26', '7', '3', 'Parry (Fencing)', 'Parrying is the act of putting your sword " +
                    "between yourself and your enemies strikes. This Knack can be used as your Defense Knack while " +
                    "you are wielding a sword.' " +
                    "UNION ALL SELECT '27', '7', '3', 'Attack (Fencing)', 'Attack is simply the ability to hit your enemy' " +
                    "UNION ALL SELECT '28', '1', '4', 'Attack (Fencing)', 'Attack is simply the ability to hit your enemy' " +
                    "UNION ALL SELECT '29', '1', '4', 'Parry (Fencing)', 'Parrying is the act of putting your sword " +
                    "between yourself and your enemies strikes. This Knack can be used as your Defense Knack while you " +
                    "are wielding a sword.' " +
                    "UNION ALL SELECT '30', '2', '4', 'Attack (Dirty Fighting)', 'Attack is simply the ability to hit " +
                    "your enemy, and should be used in all Skill uses not covered by any other mechanic. Remember, a " +
                    "bare-handed attacks Damage Rating is 0k1.'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns ObservableList of swordsman knacks from a specified swordsman school id
     *
     * @param searchSchool_id swordsman school id to return swordsman knacks for
     * @return ObservableList<SwordsmanKnack> with a school with the searched id
     */
    public static ObservableList<SwordsmanKnack> getInitKnacksForSwordsman(int searchSchool_id) {
        ObservableList<SwordsmanKnack> knacksForSwordsman = FXCollections.observableArrayList();
        try {
            String sql = "SELECT K.id, K.skill_id, K.school_id, K.name, K.description " +
                    "FROM swordsman_knacks K " +
                    "WHERE K.school_id = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, String.valueOf(searchSchool_id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int skill_id = rs.getInt("skill_id");
                int school_id = rs.getInt("school_id");
                String name = rs.getString("name");
                int knackLevel = 1;
                String description = rs.getString("description");
                //SwordsmanKnack(int id, int skill_id, int school_id, String name, int knackLevel, String description)
                SwordsmanKnack swordsmanKnack = new SwordsmanKnack(id, skill_id, school_id, name, knackLevel, description);
                knacksForSwordsman.add(swordsmanKnack);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return knacksForSwordsman;
    }
}

