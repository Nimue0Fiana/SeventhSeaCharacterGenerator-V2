package com.example.seventhseagenerator.Controllers;

/**
 * Controller Class for 'sorceryKnacksPage-view.fxml'. Allows full or half-blooded sorcerers spend their assigned sorcery
 * points
 */

import com.example.seventhseagenerator.DBAccess.DBSorceryDegree;
import com.example.seventhseagenerator.Models.SorceryKnack;
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

import static com.example.seventhseagenerator.Controllers.confirmSorceryController.tempSorcerer;

public class sorceryKnacksController implements Initializable {
    protected static ObservableList<SorceryKnack> sorceryKnacks = tempSorcerer.getSorceryKnacks1();
    int initSorceryPoints1 = tempSorcerer.getSorceryPoints1();
    @FXML // fx:id="continueButton"
    private Button continueButton; // Value injected by FXMLLoader

    @FXML // fx:id="sorceryPointsTotal"
    private Label sorceryPointsTotal; // Value injected by FXMLLoader
    @FXML // fx:id="knackName"
    private Label knackName; // Value injected by FXMLLoader
    @FXML // fx:id="knackDescription"
    private Label knackDescription; // Value injected by FXMLLoader

    @FXML // fx:id="knackNameCol"
    private TableColumn<SorceryKnack, String> knackNameCol; // Value injected by FXMLLoader

    @FXML // fx:id="knackTable"
    private TableView<SorceryKnack> knackTable; // Value injected by FXMLLoader

    @FXML // fx:id="rankValue"
    private Spinner<Integer> rankValue = new Spinner<>(0, 3, 0); // Value injected by FXMLLoader

    @FXML // fx:id="knackRankCol"
    private TableColumn<SorceryKnack, Integer> knackRankCol; // Value injected by FXMLLoader

    @FXML // fx:id="updateButton"
    private Button updateButton; // Value injected by FXMLLoader

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sorceryPointsTotal.setText((String.valueOf(initSorceryPoints1)));
        knackTable.setItems(sorceryKnacks);

        knackNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        knackRankCol.setCellValueFactory(new PropertyValueFactory<>("knackLevel"));
        knackTable.setOnMouseClicked((MouseEvent event) -> {
            tempSorcerer.getSorceryKnacks1();
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                knackName.setVisible(true);
                knackName.setText(knackTable.getSelectionModel().getSelectedItem().getName());
                knackDescription.setVisible(true);
                knackDescription.setText(knackTable.getSelectionModel().getSelectedItem().getDescription());
            }
        });
    }

    /**
     * Check that the user hasn't spent more than their alotted points, sends to next screen
     *
     * @param event
     */
    @FXML
    void onContinueButton(ActionEvent event) {
        if (tempSorcerer.getSorceryPoints1() < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You've spent too many points.\n Decrease some Knacks and try again");
            alert.showAndWait();
        } else {
            tempSorcerer.setSorceryKnacks1(sorceryKnacks);
            tempSorcerer.addSorceryDegree(DBSorceryDegree.getSorceryDegreeBySorcery(tempSorcerer.getSorcery().getId()));
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/seventhseagenerator/confirmSwordSchoolPage-view.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Checks that the user has selected a knack to update, then changes the knack level to
     * the selected value.
     *
     * @param event
     */
    @FXML
    void onUpdateButton(ActionEvent event) {
        if (knackTable.getSelectionModel().getSelectedItem() != null) {
            int prevKnackLevel = knackTable.getSelectionModel().getSelectedItem().getKnackLevel();
            knackTable.getSelectionModel().getSelectedItem().setKnackLevel(rankValue.getValue());
            knackTable.refresh();
            int newKnackLevel = knackTable.getSelectionModel().getSelectedItem().getKnackLevel();
            initSorceryPoints1 = initSorceryPoints1 + (prevKnackLevel - newKnackLevel);
            sorceryPointsTotal.setText(String.valueOf(initSorceryPoints1));
            tempSorcerer.setSorceryPoints1(initSorceryPoints1);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select a knack to purchase.");
            alert.showAndWait();
        }
    }

}
