package com.example.seventhseagenerator;

import com.example.seventhseagenerator.DBAccess.DBCreate;
import com.example.seventhseagenerator.Helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

//TODO:May later increase cost of "advanced" knacks - outside scope of current application
//TODO:increase favored trait by 1 in initialize method of trait page - outside scope of current application
//TODO:if gender is male and Vodacce, present choice to take half magic for full cost - different page - outside scope of current application
//TODO:set value of rankValue spinner on knacks page to the rank of the selected object in the tableView. Outside the scope of this project.
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("landingPage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 715, 433);
        stage.setTitle("7th Sea Character Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        String localAppPath = System.getenv("LOCALAPPDATA");
        String DBFileName = "\\seventhSeaCharacterGen.db";
        File tmpDir = new File(localAppPath + DBFileName);
        boolean exists = tmpDir.exists();
        JDBC.openConnection();
        if (!exists) {
            DBCreate.createTables();
        }
        launch(args);
        JDBC.closeConnection();
    }
}