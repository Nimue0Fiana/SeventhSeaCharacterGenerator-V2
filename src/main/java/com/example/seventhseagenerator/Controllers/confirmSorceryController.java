package com.example.seventhseagenerator.Controllers;

/**
 * Sample Skeleton for 'confirmSorceryPage.fxml' Controller Class
 */

import com.example.seventhseagenerator.DBAccess.DBSorceryDegree;
import com.example.seventhseagenerator.DBAccess.DBSorceryKnack;
import com.example.seventhseagenerator.Models.Sorcerer;
import com.example.seventhseagenerator.Models.Sorcery;
import com.example.seventhseagenerator.Models.SorceryDegree;
import com.example.seventhseagenerator.Models.SorceryKnack;
import com.example.seventhseagenerator.Models.Sorcerer;
import com.example.seventhseagenerator.Models.Sorcery;
import com.example.seventhseagenerator.Models.SorceryDegree;
import com.example.seventhseagenerator.Models.SorceryKnack;
import javafx.collections.ObservableList;
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

import static com.example.seventhseagenerator.Controllers.personalInfoController.tempCharacter;

//TODO: Add sorcery degrees here
public class confirmSorceryController implements Initializable {
    String nextPage;
    boolean isSorcerer = false;
    public static Sorcerer tempSorcerer = new Sorcerer();
    ObservableList<SorceryDegree> sorceryDegrees;
    int blood;
    Sorcery sorcery = new Sorcery();
    @FXML //fx:id = "heroPointsTotal"
    private Label heroPointsTotal; // Value injected by FXMLLoader

    @FXML // fx:id="continueButton"
    private Button continueButton; // Value injected by FXMLLoader

    @FXML // fx:id="fullBlooded"
    private Button fullBlooded; // Value injected by FXMLLoader

    @FXML // fx:id="halfBlooded"
    private Button halfBlooded; // Value injected by FXMLLoader

    @FXML // fx:id="noSorcery"
    private Button noSorcery; // Value injected by FXMLLoader

    @FXML // fx:id="sorceryChoiceDescription"
    private Label sorceryChoiceDescription; // Value injected by FXMLLoader
    @FXML // fx:id="costLabel"
    private Label costLabel; // Value injected by FXMLLoader

    @FXML // fx:id="twiceBlooded"
    private Button twiceBlooded; // Value injected by FXMLLoader

    /**
     * Populates current hero point total
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        heroPointsTotal.setText(String.valueOf(tempCharacter.getHeroPoints()));
    }

    /**
     * Chooses full-blooded, displays cost and description
     *
     * @param actionEvent
     */
    @FXML
    public void onFullBlooded(ActionEvent actionEvent) {
        isSorcerer = true;
        blood = 2;
        nextPage = "/com/example/seventhseagenerator/sorceryKnacksPage-view.fxml";
        sorceryChoiceDescription.setVisible(true);
        sorceryChoiceDescription.setText("Will allow access to highest ranked magic");
        costLabel.setVisible(true);
        costLabel.setText("Cost: 40 Hero Points");
    }

    /**
     * Chooses half-blooded, displays description and cost
     *
     * @param actionEvent
     */
    @FXML
    public void onHalfBlooded(ActionEvent actionEvent) {
        isSorcerer = true;
        blood = 1;
        nextPage = "/com/example/seventhseagenerator/sorceryKnacksPage-view.fxml";
        sorceryChoiceDescription.setVisible(true);
        sorceryChoiceDescription.setText("Your character can only ever access the first rank in magical knacks");
        costLabel.setVisible(true);
        costLabel.setText("Cost: 20 Hero Points");
    }

    /**
     * Chooses twice-blooded, displays description and cost
     *
     * @param actionEvent
     */
    @FXML
    public void onTwiceBlooded(ActionEvent actionEvent) {
        isSorcerer = true;
        blood = 3;
        nextPage = "/com/example/seventhseagenerator/chooseSorceryNation-view.fxml";
        sorceryChoiceDescription.setVisible(true);
        sorceryChoiceDescription.setText("Your character can access magic from two bloodlines, but can only use the first rank of those knacks");
        costLabel.setVisible(true);
        costLabel.setText("Cost: 40 Hero Points");
    }

    /**
     * Chooses to not have sorcery. Displays description
     *
     * @param actionEvent
     */
    @FXML
    public void onNoSorcery(ActionEvent actionEvent) {
        isSorcerer = false;
        nextPage = "/com/example/seventhseagenerator/confirmSwordSchoolPage-view.fxml";
        sorceryChoiceDescription.setVisible(true);
        sorceryChoiceDescription.setText("Your character does not have any magical skill");
        costLabel.setVisible(true);
        costLabel.setText("Cost: 0 Hero Points");
    }

    /**
     * Sets sorcery choice.
     *
     * @param event
     */
    @FXML
    void onContinue(ActionEvent event) {
        if (nextPage == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please choose an option.");
            alert.showAndWait();
        } else {
            System.out.println(blood);
            if (isSorcerer) {
                tempCharacter.setSorcerer(true);
                tempSorcerer = tempSorcerer.transformPCToSorcerer(tempCharacter);
                tempSorcerer.setBlood(blood);
                sorcery = tempSorcerer.getNation().getSorcery();
                SorceryDegree newSorceryDegree = DBSorceryDegree.getSorceryDegreeBySorcery(sorcery.getId());
                tempSorcerer.addSorceryDegree(newSorceryDegree);
                ObservableList<SorceryKnack> sorceryKnacks = DBSorceryKnack.getAllKnacksForSorcery(sorcery.getId());
                tempSorcerer.setSorceryKnacks1(sorceryKnacks);
                if (blood == 2) {
                    tempSorcerer.setSorcery(sorcery);
                    tempSorcerer.setHeroPoints(tempSorcerer.getHeroPoints() - 40);
                    tempSorcerer.setSorceryPoints1(7);
                } else if (blood == 1) {
                    tempSorcerer.setSorcery(sorcery);
                    tempSorcerer.setHeroPoints(tempSorcerer.getHeroPoints() - 20);
                    tempSorcerer.setSorceryPoints1(3);
                } else if (blood == 3) {
                    tempSorcerer.addSorcery(sorcery);
                    tempSorcerer.setHeroPoints(tempSorcerer.getHeroPoints() - 40);
                    tempSorcerer.setSorceryPoints1(3);
                    tempSorcerer.setSorceryPoints2(3);
                }
            } else {
                tempCharacter.setSorcerer(false);
            }
            if (nextPage == null) {
                sorceryChoiceDescription.setVisible(true);
                sorceryChoiceDescription.setStyle("-fx-text-fill:RED;-fx-font-weight: bold;");
                sorceryChoiceDescription.setText("No choice detected. Please choose an option below before continuing.");
            } else {
                System.out.println(tempSorcerer);
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
}
