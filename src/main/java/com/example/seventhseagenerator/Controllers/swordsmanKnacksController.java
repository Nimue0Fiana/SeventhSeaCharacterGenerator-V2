/**
 * Controller Class for 'swordKnacksPage-view.fxml'. Allows users to spend hero points on knacks obtained from attending
 * swordsman school
 */

package com.example.seventhseagenerator.Controllers;

import com.example.seventhseagenerator.Models.SwordsmanKnack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.seventhseagenerator.Controllers.confirmSwordController.tempSwordSorcerer;
import static com.example.seventhseagenerator.Controllers.confirmSwordController.tempSwordsman;
import static com.example.seventhseagenerator.Controllers.personalInfoController.tempCharacter;

public class swordsmanKnacksController implements Initializable {
    private int initialKnackTotal = 0;
    private int initHeroPoints;
    protected static ObservableList<SwordsmanKnack> swordsmanKnacks = FXCollections.observableArrayList();
    @FXML // fx:id="continueButton"
    private Button continueButton; // Value injected by FXMLLoader

    @FXML // fx:id="knackDescription"
    private Label knackDescription; // Value injected by FXMLLoader

    @FXML // fx:id="knackName"
    private Label knackName; // Value injected by FXMLLoader

    @FXML // fx:id="knackNameCol"
    private TableColumn<?, ?> knackNameCol; // Value injected by FXMLLoader

    @FXML // fx:id="knackRankCol"
    private TableColumn<?, ?> knackRankCol; // Value injected by FXMLLoader

    @FXML // fx:id="knackTable"
    private TableView<SwordsmanKnack> knackTable; // Value injected by FXMLLoader

    @FXML // fx:id="rankValue"
    private Spinner<Integer> rankValue; // Value injected by FXMLLoader

    @FXML // fx:id="heroPointsTotal"
    private Label heroPointsTotal; // Value injected by FXMLLoader

    @FXML // fx:id="updateButton"
    private Button updateButton; // Value injected by FXMLLoader

    /**
     * Populates hero point total and swordsman knacks table
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (tempCharacter.isSorcerer()) {
            heroPointsTotal.setText((String.valueOf(tempSwordSorcerer.getHeroPoints())));
            swordsmanKnacks = tempSwordSorcerer.getSwordsmanKnacks();
            initHeroPoints = tempSwordSorcerer.getHeroPoints();
            System.out.println(swordsmanKnacks);
            System.out.println(tempSwordSorcerer);
        } else {
            heroPointsTotal.setText(String.valueOf(tempSwordsman.getHeroPoints()));
            swordsmanKnacks = tempSwordsman.getSwordsmanKnacks();
            initHeroPoints = tempSwordsman.getHeroPoints();
            System.out.println(swordsmanKnacks);
            System.out.println(tempSwordsman);
        }
        knackTable.setItems(swordsmanKnacks);

        knackNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        knackRankCol.setCellValueFactory(new PropertyValueFactory<>("knackLevel"));
        knackTable.setOnMouseClicked((MouseEvent event) -> {
            if (tempCharacter.isSorcerer()) {
                tempSwordSorcerer.getSwordsmanKnacks();
            } else {
                tempSwordsman.getSwordsmanKnacks();
            }
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                knackName.setVisible(true);
                knackName.setText(knackTable.getSelectionModel().getSelectedItem().getName());
                knackDescription.setVisible(true);
                knackDescription.setText(knackTable.getSelectionModel().getSelectedItem().getDescription());
            }
        });
        for (SwordsmanKnack sk : swordsmanKnacks) {
            initialKnackTotal++;
        }
    }

    /**
     * Updates swordsman knack to selected value. Confirms a knack has been selected.
     *
     * @param event
     */
    @FXML
    void onUpdateButton(ActionEvent event) {
        SwordsmanKnack selectedKnack = knackTable.getSelectionModel().getSelectedItem();
        if (selectedKnack == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please choose a knack and try again.");
            alert.showAndWait();
        } else {
            int heroPoints;
            int currKnackTotal = 0;
            knackTable.getSelectionModel().getSelectedItem().setKnackLevel(rankValue.getValue());
            knackTable.refresh();

            for (SwordsmanKnack sk : swordsmanKnacks) {
                currKnackTotal += sk.getKnackLevel();
            }
            System.out.println("current knack total: " + currKnackTotal);
            if (tempCharacter.isSorcerer()) {

                heroPoints = initHeroPoints - (currKnackTotal - initialKnackTotal);
                heroPointsTotal.setText(String.valueOf(heroPoints));
                tempSwordSorcerer.setHeroPoints(heroPoints);
            } else {
                heroPoints = initHeroPoints - (currKnackTotal - initialKnackTotal);
                heroPointsTotal.setText(String.valueOf(heroPoints));
                tempSwordsman.setHeroPoints(heroPoints);
            }
        }
    }

    /**
     * Validates that the user has not spend too many points.
     *
     * @param event
     */
    @FXML
    void onContinueButton(ActionEvent event) {
        System.out.println(tempSwordsman);
        System.out.println(tempSwordSorcerer);
        if (initHeroPoints < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You've spent too many points.\n Reduce some skills and try again.");
            alert.showAndWait();
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/seventhseagenerator/increaseTraitsPage-view.fxml"));
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
