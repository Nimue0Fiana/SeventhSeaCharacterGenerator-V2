package com.example.seventhseagenerator.Controllers;
/**
 * Sample Skeleton for 'landingPage.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class landingController {

    @FXML // fx:id="newCharacter"
    private Button newCharacter; // Value injected by FXMLLoader

    @FXML // fx:id="savedCharacter"
    private Button savedCharacter; // Value injected by FXMLLoader

    /**
     * Starts to build a new character
     *
     * @param event
     */
    @FXML
    void onNewCharacter(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/seventhseagenerator/personalInfoPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * View report of all saved characters
     *
     * @param event
     */
    @FXML
    void onSavedCharacter(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/seventhseagenerator/savedCharactersReport-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

