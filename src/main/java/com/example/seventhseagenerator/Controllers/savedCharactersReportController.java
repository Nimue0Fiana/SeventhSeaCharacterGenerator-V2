/**
 * Controller Class for 'savedCharactersReport-view.fxml'. Shows report of all characters created that are still saved.
 * Allows access to edit some characteristics of player characters. Only items that do not cause other characteristics
 * to need changing can be edited.
 */

package com.example.seventhseagenerator.Controllers;

import com.example.seventhseagenerator.DBAccess.DBPlayerCharacter;
import com.example.seventhseagenerator.Models.PlayerCharacter;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.seventhseagenerator.Controllers.personalInfoController.tempCharacter;

public class savedCharactersReportController implements Initializable {

    ObservableList<PlayerCharacter> allPlayerCharacters = FXCollections.observableArrayList();

    @FXML // fx:id="charTable"
    private TableView<PlayerCharacter> charTable; // Value injected by FXMLLoader

    @FXML // fx:id="charNameCol"
    private TableColumn<?, ?> charNameCol; // Value injected by FXMLLoader
    @FXML // fx:id="playerCol"
    private TableColumn<?, ?> playerCol; // Value injected by FXMLLoader
    @FXML // fx:id="genderCol"
    private TableColumn<?, ?> genderCol; // Value injected by FXMLLoader
    @FXML // fx:id="nationCol"
    private TableColumn<?, ?> nationCol; // Value injected by FXMLLoader
    @FXML // fx:id="pointsCol"
    private TableColumn<?, ?> pointsCol; // Value injected by FXMLLoader
    @FXML // fx:id="timeCol"
    private TableColumn<?, ?> timeCol; // Value injected by FXMLLoader

    @FXML // fx:id="continueButton"
    private Button continueButton; // Value injected by FXMLLoader

    /**
     * Loads name, player name, gender, nationality, unspent points and creation time to table
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allPlayerCharacters = DBPlayerCharacter.getAllCharacters();
        charTable.setItems(allPlayerCharacters);
        charNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerCol.setCellValueFactory(new PropertyValueFactory<>("player"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        nationCol.setCellValueFactory(new PropertyValueFactory<>("nationName"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("heroPoints"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
    }

    /**
     * Takes user back to landing page
     *
     * @param event
     */
    @FXML
    void onBackButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/seventhseagenerator/landingPage-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Confirms the user wants to delete the character, and if confirmed, deletes character
     *
     * @param event
     */
    @FXML
    void onDeleteButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please confirm you want to permanently delete this character.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int charId = charTable.getSelectionModel().getSelectedItem().getId();
            DBPlayerCharacter.deleteCharacterById(charId);
            charTable.refresh();
        } else {
            //Do nothing
        }
    }

    /**
     * Takes user to edit the character
     *
     * @param event
     */
    @FXML
    void onEditButton(ActionEvent event) {
        int charId = charTable.getSelectionModel().getSelectedItem().getId();

        tempCharacter = DBPlayerCharacter.getSimpleCharacterById(charId);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/seventhseagenerator/personalInfoEdit-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
