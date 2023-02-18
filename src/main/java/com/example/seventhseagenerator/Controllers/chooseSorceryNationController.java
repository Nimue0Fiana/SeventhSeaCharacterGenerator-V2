package com.example.seventhseagenerator.Controllers;

/**
 * Sample Skeleton for 'chooseSorceryNation.fxml' Controller Class
 */

import com.example.seventhseagenerator.DBAccess.DBNation;
import com.example.seventhseagenerator.DBAccess.DBSorcery;
import com.example.seventhseagenerator.DBAccess.DBSorceryDegree;
import com.example.seventhseagenerator.DBAccess.DBSorceryKnack;
import com.example.seventhseagenerator.Models.Nation;
import com.example.seventhseagenerator.Models.Sorcery;
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

import static com.example.seventhseagenerator.Controllers.confirmSorceryController.tempSorcerer;

public class chooseSorceryNationController implements Initializable {
    Nation nation = null;

    ObservableList<Sorcery> sorceries = tempSorcerer.getSorceries();
    String characterNation;
    Sorcery sorcery;
    @FXML // fx:id="nation2Description"
    private Label nation2Description; // Value injected by FXMLLoader
    @FXML // fx:id="avalon"
    private Button avalon; // Value injected by FXMLLoader


    @FXML // fx:id="continueButton"
    private Button continueButton; // Value injected by FXMLLoader

    @FXML // fx:id="montaigne"
    private Button montaigne; // Value injected by FXMLLoader

    @FXML // fx:id="vodacce"
    private Button vodacce; // Value injected by FXMLLoader

    /**
     * disables the buttons for sorceries a player character is not eligible for
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        characterNation = tempSorcerer.getNation().getNation_name();
        if (characterNation.equals("Vodacce") || (tempSorcerer.getGender().equals("male"))) {
            vodacce.setDisable(true);
        }
        if (characterNation.equals("Montaigne")) {
            montaigne.setDisable(true);
        }
        if (characterNation.equals("Avalon")) {
            avalon.setDisable(true);
        }

    }

    /**
     * Selects glamour
     *
     * @param event
     */
    @FXML
    void onAvalon(ActionEvent event) {
        nation = DBNation.getNationByName("Avalon");
        nation2Description.setText(nation.getDescription());
        nation2Description.setVisible(true);
    }

    /**
     * Selects Porte
     *
     * @param event
     */
    @FXML
    void onMontaigne(ActionEvent event) {
        nation = DBNation.getNationByName("Montaigne");
        nation2Description.setText(nation.getDescription());
        nation2Description.setVisible(true);
    }

    /**
     * Selects Sorte
     *
     * @param event
     */
    @FXML
    void onVodacce(ActionEvent event) {
        nation = DBNation.getNationByName("Vodacce");
        nation2Description.setText(nation.getDescription());
        nation2Description.setVisible(true);
    }

    /**
     * Sets second sorcery type
     *
     * @param event
     */
    @FXML
    void onClick(ActionEvent event) {
        if (nation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please choose a nation to use as your second sorcery.");
            alert.showAndWait();
        } else {
            sorcery = DBSorcery.getSorceryByNationId(nation.getId());
            tempSorcerer.addSorcery(sorcery);
            tempSorcerer.addSorceryDegree(DBSorceryDegree.getSorceryDegreeBySorcery(sorcery.getId()));
            ObservableList<SorceryKnack> sorceryKnacks = DBSorceryKnack.getAllKnacksForSorcery(sorcery.getId());
            tempSorcerer.setSorceryKnacks2(sorceryKnacks);
            System.out.println(tempSorcerer.getSorceries().size());

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/seventhseagenerator/TwiceBloodedSorceryKnack-view.fxml"));
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
