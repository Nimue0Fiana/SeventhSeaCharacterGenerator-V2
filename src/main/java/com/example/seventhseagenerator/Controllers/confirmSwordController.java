package com.example.seventhseagenerator.Controllers;

/**
 * Sample Skeleton for 'confirmSwordSchoolPage.fxml' Controller Class
 */

import com.example.seventhseagenerator.DBAccess.DBSwordsmanDegree;
import com.example.seventhseagenerator.DBAccess.DBSwordsmanKnack;
import com.example.seventhseagenerator.DBAccess.DBSwordsmanSchool;
import com.example.seventhseagenerator.Models.SwordAndSorcerer;
import com.example.seventhseagenerator.Models.Swordsman;
import com.example.seventhseagenerator.Models.SwordsmanSchool;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.seventhseagenerator.Controllers.confirmSorceryController.tempSorcerer;
import static com.example.seventhseagenerator.Controllers.personalInfoController.tempCharacter;

public class confirmSwordController implements Initializable {
    public static SwordAndSorcerer tempSwordSorcerer = new SwordAndSorcerer();
    public static Swordsman tempSwordsman = new Swordsman();
    SwordsmanSchool swordsmanSchool = null;
    private int heroType; //0=playerCharacter, 1=sorcerer, 2=swordsman, 3=both sorcerer and swordsman
    private boolean isSwordsman;
    private int nationId;
    String schoolName;
    String schoolDesc;
    String nextPage;
    @FXML // fx:id heroPointsTotal
    private Label heroPointsTotal; //Value injected by FXMLLoader
    @FXML // fx:id="continueButton"
    private Button continueButton; // Value injected by FXMLLoader

    @FXML // fx:id="myNation"
    private Button myNation; // Value injected by FXMLLoader

    @FXML // fx:id="noSwordSchool"
    private Button noSwordSchool; // Value injected by FXMLLoader

    @FXML // fx:id="otherNation"
    private Button otherNation; // Value injected by FXMLLoader

    @FXML // fx:id="swordSchoolDescription"
    private Label swordSchoolDescription; // Value injected by FXMLLoader

    /**
     * Checks Player Character type -inherited type Sorcerer or not.
     * determines which school the Player Character will be if using native school
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (tempCharacter.isSorcerer()) {
            heroType = 1;
            heroPointsTotal.setText(String.valueOf(tempSorcerer.getHeroPoints()));
            nationId = tempSorcerer.getNation().getId();
        } else {
            heroType = 0;
            heroPointsTotal.setText(String.valueOf(tempCharacter.getHeroPoints()));
            nationId = tempCharacter.getNation().getId();
        }
    }

    /**
     * Choose native swordsman school
     *
     * @param event
     */
    @FXML
    void onMyNation(ActionEvent event) {
        isSwordsman = true;
        swordSchoolDescription.setVisible(true);
        swordsmanSchool = DBSwordsmanSchool.getSwordSchoolById(nationId);
        schoolName = DBSwordsmanSchool.getSwordSchoolNameById(nationId);
        schoolDesc = DBSwordsmanSchool.getSwordSchoolDescById(nationId);
        swordSchoolDescription.setText("Cost: 25 points\nSchool: " + schoolName + " - " + schoolDesc);
        nextPage = "/com/example/seventhseagenerator/swordKnacksPage-view.fxml";
    }

    /**
     * Choose non-native school
     *
     * @param event
     */
    @FXML
    void onOther(ActionEvent event) {
        isSwordsman = true;
        swordsmanSchool = null;
        swordSchoolDescription.setText("Cost: 35 points");
        nextPage = "/com/example/seventhseagenerator/chooseSwordsmanNation-view.fxml";
    }

    /**
     * Do not set school
     *
     * @param event
     */
    @FXML
    void onNoSwordSchool(ActionEvent event) {
        isSwordsman = false;
        swordsmanSchool = null;
        nextPage = "/com/example/seventhseagenerator/increaseTraitsPage-view.fxml";
    }

    /**
     * If choosing native school, changes character to inherited Swordsman type or Swordsman-Sorcerer type
     *
     * @param event
     */
    @FXML
    void onContinue(ActionEvent event) {
        if (nextPage == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please choose an option.");
            alert.showAndWait();
        } else {
            tempCharacter.setSwordsman(isSwordsman);
            //if character is a swordsman
            if (tempCharacter.isSwordsman()) {
                //if character is also a sorcerer
                if (tempCharacter.isSorcerer()) {
                    //if that sorcerer is double-blooded
                    if (tempSorcerer.getBlood() == 3) {
                        tempSwordSorcerer = tempSwordSorcerer.transformDoubleSorcererToSwordSorcerer(tempSorcerer);
                        //if this double-blooded sorcerer wants a school of their own nation
                        if (swordsmanSchool != null) {
                            tempSwordSorcerer.addSwordsmanSchool(swordsmanSchool);
                            tempSwordSorcerer.addSwordsmanDegree(DBSwordsmanDegree.getSwordsmanDegreeBySchoolId(swordsmanSchool.getId()));
                            tempSwordSorcerer.setSwordsmanKnacks(DBSwordsmanKnack.getInitKnacksForSwordsman(swordsmanSchool.getId()));
                            tempSwordSorcerer.setHeroPoints(tempSwordSorcerer.getHeroPoints() - 25);
                            //if the double-blooded sorcerer wants a school of a different nation
                        } else if (swordsmanSchool == null) {
                            tempSwordSorcerer.setHeroPoints(tempSwordSorcerer.getHeroPoints() - 35);
                        }
                        //if that sorcerer is not double-blooded (has only 1 type of sorcery)
                    } else {
                        //if the sorcerer wants a school of their own nation
                        if (swordsmanSchool != null) {
                            tempSwordSorcerer = tempSwordSorcerer.transformSorcererToSwordAndSorcerer(tempSorcerer);
                            tempSwordSorcerer.addSwordsmanSchool(swordsmanSchool);
                            tempSwordSorcerer.addSwordsmanDegree(DBSwordsmanDegree.getSwordsmanDegreeBySchoolId(swordsmanSchool.getId()));
                            tempSwordSorcerer.setSwordsmanKnacks(DBSwordsmanKnack.getInitKnacksForSwordsman(swordsmanSchool.getId()));
                            tempSwordSorcerer.setHeroPoints(tempSwordSorcerer.getHeroPoints() - 25);
                            //if the sorcerer wants a school of another nation
                        } else if (swordsmanSchool == null) {
                            tempSwordSorcerer = tempSwordSorcerer.transformSorcererToSwordAndSorcerer(tempSorcerer);
                            tempSwordSorcerer.setHeroPoints(tempSwordSorcerer.getHeroPoints() - 35);
                        }

                    }


                    //if the character is NOT a sorcerer
                } else if (!tempCharacter.isSorcerer()) {
                    //if the character wants a school of their own nation
                    if (swordsmanSchool != null) {
                        tempSwordsman = tempSwordsman.transformPCToSwordsman(tempCharacter);
                        tempSwordsman.addSwordsmanSchool(swordsmanSchool);
                        System.out.println(tempSwordsman.getSwordsmanSchools());
                        tempSwordsman.addSwordsmanDegree(DBSwordsmanDegree.getSwordsmanDegreeBySchoolId(swordsmanSchool.getId()));
                        tempSwordsman.setSwordsmanKnacks(DBSwordsmanKnack.getInitKnacksForSwordsman(swordsmanSchool.getId()));
                        tempSwordsman.setHeroPoints(tempSwordsman.getHeroPoints() - 25);
                        //if the character wants a school of another nation
                    } else if (swordsmanSchool == null) {
                        tempSwordsman = tempSwordsman.transformPCToSwordsman(tempCharacter);
                        tempSwordsman.setHeroPoints(tempSwordsman.getHeroPoints() - 35);
                    }

                }
                System.out.println("Temp Character: " + tempCharacter);
                System.out.println("Temp Swordsman: " + tempSwordsman.getName() + " " + tempSwordsman);
                System.out.println("Temp Sword & Sorcerer: " + tempSwordSorcerer.getName() + " " + tempSwordSorcerer);
            }


            try {
                Parent root = FXMLLoader.load(getClass().getResource(nextPage));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
