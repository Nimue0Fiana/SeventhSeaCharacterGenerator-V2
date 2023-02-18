package com.example.seventhseagenerator.Controllers;

/**
 * Sample Skeleton for 'chooseNationPage.fxml' Controller Class
 */

import com.example.seventhseagenerator.DBAccess.DBNation;
import com.example.seventhseagenerator.Models.Nation;
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

public class chooseNationController implements Initializable {
    Nation nation = null;
    String nextPage = "/com/example/seventhseagenerator/confirmSorceryPage-view.fxml";
    @FXML // fx:id="avalon"
    private Button avalon; // Value injected by FXMLLoader

    @FXML // fx:id="castille"
    private Button castille; // Value injected by FXMLLoader

    @FXML // fx:id="continueButton"
    private Button continueButton; // Value injected by FXMLLoader

    @FXML // fx:id="montaigne"
    private Button montaigne; // Value injected by FXMLLoader

    @FXML // fx:id="nationDescription"
    private Label nationDescription; // Value injected by FXMLLoader

    @FXML // fx:id="vodacce"
    private Button vodacce; // Value injected by FXMLLoader

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(tempCharacter.toString());
    }

    /**
     * Displays a description of Avalon
     *
     * @param event
     */
    @FXML
    void onAvalon(ActionEvent event) {
        nation = DBNation.getNationByName("Avalon");
        nationDescription.setText(nation.getDescription());
        nationDescription.setVisible(true);
        System.out.println(nation.toString());
        System.out.println(nation.getFavored_trait());
    }

    /**
     * Displays a description of Castille
     *
     * @param event
     */
    @FXML
    void onCastille(ActionEvent event) {
        nation = DBNation.getNationByName("Castille");
        nationDescription.setText(nation.getDescription());
        nationDescription.setVisible(true);
        System.out.println(nation.toString());
    }

    /**
     * Displays a description of Montaigne
     *
     * @param event
     */
    @FXML
    void onMontaigne(ActionEvent event) {
        nation = DBNation.getNationByName("Montaigne");
        nationDescription.setText(nation.getDescription());
        nationDescription.setVisible(true);
        System.out.println(nation.toString());
    }

    /**
     * Displays a description of Vodacce
     *
     * @param event
     */
    @FXML
    void onVodacce(ActionEvent event) {
        nation = DBNation.getNationByName("Vodacce");
        nationDescription.setText(nation.getDescription());
        nationDescription.setVisible(true);
        System.out.println(nation.toString());
    }

    /**
     * Sets the Player Character as nationality of last chosen nation
     *
     * @param event
     */
    @FXML
    void onClick(ActionEvent event) {
        if (nation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please choose a nation.");
            alert.showAndWait();
        } else {

            tempCharacter.setNation(nation);
            String trait = nation.getFavored_trait();
            if (trait.equals("Resolve")) {
                //tempCharacter.setResolve(1);
            } else if (trait.equals("Finesse")) {
                //tempCharacter.setFinesse(1);
                nextPage = "/com/example/seventhseagenerator/confirmSwordSchoolPage-view.fxml";
            } else if (trait.equals("Wits")) {
                //tempCharacter.setWits(1);
                if (tempCharacter.getGender().equals("male")) {
                    nextPage = "/com/example/seventhseagenerator/confirmSwordSchoolPage-view.fxml";
                }
            } else if (trait.equals("Panache")) {
                //tempCharacter.setPanache(1);
            } else if (trait.equals("Brawn")) {
                //tempCharacter.setBrawn(1);
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
