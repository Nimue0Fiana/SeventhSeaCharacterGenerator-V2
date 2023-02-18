package com.example.seventhseagenerator.Controllers;

/**
 * Controller Class for 'skillsPage.fxml'. Sets skills. Skills determine knacks
 */

import com.example.seventhseagenerator.DBAccess.DBKnack;
import com.example.seventhseagenerator.DBAccess.DBSkill;
import com.example.seventhseagenerator.Models.Knack;
import com.example.seventhseagenerator.Models.Skill;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.seventhseagenerator.Controllers.confirmSorceryController.tempSorcerer;
import static com.example.seventhseagenerator.Controllers.confirmSwordController.tempSwordSorcerer;
import static com.example.seventhseagenerator.Controllers.confirmSwordController.tempSwordsman;
import static com.example.seventhseagenerator.Controllers.personalInfoController.tempCharacter;

public class skillsController implements Initializable {
    @FXML // fx:id= "searchField"
    public TextField searchField; //Value injected by FXMLLoader
    private int initHeroPoints;
    private final ObservableList<Skill> allSkills = DBSkill.getAllSkills();
    private final ObservableList<Skill> purchasedSkills = FXCollections.observableArrayList();
    private final ObservableList<Knack> purchasedKnacks = FXCollections.observableArrayList();
    private ObservableList<Knack> associatedKnacks = FXCollections.observableArrayList();
    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="availKnacksCol"
    private TableColumn<?, ?> availKnacksCol; // Value injected by FXMLLoader

    @FXML // fx:id="availableSkillNameCol"
    private TableColumn<?, ?> availableSkillNameCol; // Value injected by FXMLLoader

    @FXML // fx:id="availableSkillsTable"
    private TableView<Skill> availableSkillsTable; // Value injected by FXMLLoader

    @FXML // fx:id="chosenAttachedKnackCol"
    private TableColumn<?, ?> chosenAttachedKnackCol; // Value injected by FXMLLoader

    @FXML // fx:id="chosenSkillCol"
    private TableColumn<?, ?> chosenSkillCol; // Value injected by FXMLLoader

    @FXML // fx:id="chosenSkillsTable"
    private TableView<Skill> chosenSkillsTable; // Value injected by FXMLLoader

    @FXML // fx:id="continueButton"
    private Button continueButton; // Value injected by FXMLLoader

    @FXML // fx:id="heroPointsTotal"
    private Label heroPointsTotal; // Value injected by FXMLLoader

    @FXML // fx:id="skillsList"
    private Label skillsList; //Value injected by FXMLLoader

    /**
     * Sets display of total hero points. Populates table with available skills
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (tempCharacter.isSorcerer() && tempCharacter.isSwordsman()) {
            initHeroPoints = tempSwordSorcerer.getHeroPoints();
            //purchasedAdvantages = tempSwordSorcerer.getAdvantages();
        } else if (tempCharacter.isSwordsman()) {
            initHeroPoints = tempSwordsman.getHeroPoints();
            //purchasedAdvantages = tempSwordsman.getAdvantages();
        } else if (tempCharacter.isSorcerer()) {
            initHeroPoints = tempSorcerer.getHeroPoints();
            //purchasedAdvantages = tempSorcerer.getAdvantages();
        } else {
            initHeroPoints = tempCharacter.getHeroPoints();
            //purchasedAdvantages = tempCharacter.getAdvantages();
        }
        heroPointsTotal.setText(String.valueOf(initHeroPoints));

        availableSkillsTable.setItems(allSkills);
        availableSkillNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        availableSkillsTable.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                skillsList.setVisible(true);
                skillsList.setText("This skill includes the following knacks: " + DBKnack.allKnacksForSkill(availableSkillsTable.getSelectionModel().getSelectedItem().getId()));
            }
        });
        chosenSkillsTable.setItems(purchasedSkills);
        chosenSkillCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    /**
     * Adds a skill to chosen skills table, updates available hero points
     *
     * @param actionEvent
     */
    public void onAddButton(ActionEvent actionEvent) {
        Skill selectedSkill = availableSkillsTable.getSelectionModel().getSelectedItem();
        if (selectedSkill == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a skill to add.");
            alert.showAndWait();
        } else {
            purchasedSkills.add(selectedSkill);
            allSkills.remove(selectedSkill);
            initHeroPoints = initHeroPoints - 2;
            heroPointsTotal.setText(String.valueOf(initHeroPoints));
            associatedKnacks = DBKnack.knackListForSkill(selectedSkill.getId());

            for (Knack k : associatedKnacks) {
                boolean matched = false;
                //System.out.println("Associated Knack (outer list): " + k.getName());
                for (Knack checkedKnack : purchasedKnacks) {

                    if (k.getName().equals(checkedKnack.getName())) {
                        matched = true;
                        //System.out.println("Matched " + checkedKnack.getName() + " to outer list " + k.getName());
                        checkedKnack.setKnackLevel(checkedKnack.getKnackLevel() + 1);
                    }
                }
                if (!matched) {
                    //System.out.println("Purchased Knack " + k.getName());
                    k.setKnackLevel(k.getKnackLevel() + 1);
                    purchasedKnacks.add(k);
                }
            }
        }

    }

    /**
     * Removes a skill from the chosen skills table. Updates hero points label
     *
     * @param actionEvent
     */
    public void onRemoveButton(ActionEvent actionEvent) {
        Skill selectedSkill = chosenSkillsTable.getSelectionModel().getSelectedItem();
        if (selectedSkill == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a skill to remove.");
            alert.showAndWait();
        } else {
            allSkills.add(selectedSkill);
            purchasedSkills.remove(selectedSkill);
            initHeroPoints = initHeroPoints + 2;
            heroPointsTotal.setText(String.valueOf(initHeroPoints));
            //associatedKnacks = DBKnack.knackListForSkill(selectedSkill.getId());
            //clears all knacks
            purchasedKnacks.removeAll(purchasedKnacks);
            for (Skill s : purchasedSkills) {
                associatedKnacks = DBKnack.knackListForSkill(s.getId());
                for (Knack k : associatedKnacks
                ) {
                    boolean matched = false;
                    //System.out.println("Associated Knack (outer list): " + k.getName());
                    for (Knack checkedKnack : purchasedKnacks) {

                        if (k.getName().equals(checkedKnack.getName())) {
                            matched = true;
                            //System.out.println("Matched " + checkedKnack.getName() + " to outer list " + k.getName());
                            checkedKnack.setKnackLevel(checkedKnack.getKnackLevel() + 1);
                        }
                    }
                    if (!matched) {
                        //System.out.println("Purchased Knack " + k.getName());
                        k.setKnackLevel(k.getKnackLevel() + 1);
                        purchasedKnacks.add(k);
                    }
                }
            }


            System.out.println(purchasedKnacks.toString());
        }
    }

    /**
     * Sets skills, creates initial level for knacks
     *
     * @param event
     */
    @FXML
    void onContinue(ActionEvent event) {
        if (initHeroPoints < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You've spent too many points.\n Remove some skills and try again.");
            alert.showAndWait();
        } else {
            if (tempCharacter.isSorcerer() && tempCharacter.isSwordsman()) {
                tempSwordSorcerer.setHeroPoints(initHeroPoints);
                tempSwordSorcerer.setSkills(purchasedSkills);
                tempSwordSorcerer.setKnacks(purchasedKnacks);
            } else if (tempCharacter.isSwordsman()) {
                tempSwordsman.setHeroPoints(initHeroPoints);
                tempSwordsman.setSkills(purchasedSkills);
                tempSwordsman.setKnacks(purchasedKnacks);
            } else if (tempCharacter.isSorcerer()) {
                tempSorcerer.setHeroPoints(initHeroPoints);
                tempSorcerer.setSkills(purchasedSkills);
                tempSorcerer.setKnacks(purchasedKnacks);
            } else {
                tempCharacter.setHeroPoints(initHeroPoints);
                tempCharacter.setSkills(purchasedSkills);
                tempCharacter.setKnacks(purchasedKnacks);
            }
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/seventhseagenerator/knacksPage-view.fxml"));
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
     * Searches skill list for skill id or skill name
     *
     * @param keyEvent
     */
    public void onEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (searchField.getText().isBlank()) {

            } else {
                try {
                    String q = searchField.getText();
                    ObservableList<Skill> skills = DBSkill.lookupSkill(q);
                    if (skills.size() == 0) {
                        int skillId = Integer.parseInt(q);
                        Skill s = DBSkill.lookupSkillById(skillId);
                        if (s != null) {
                            skills.add(s);
                        } else if (s == null) {
                            throw new Exception("No results found with that ID.");
                        }
                    }
                    availableSkillsTable.setItems(skills);

                } catch (NumberFormatException excpt) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText("We couldn't find a name matching that entry. \nPlease try again.");
                    alert.showAndWait();
                } catch (Exception excpt) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setContentText(excpt.getMessage() + "\nPlease try again.");
                    alert.showAndWait();
                }
            }
        }
    }

    /**
     * Searches skill list for skill id or skill name
     *
     * @param actionEvent
     */
    public void onSearchButton(ActionEvent actionEvent) {
        if (searchField.getText().isBlank()) {

        } else {
            try {
                String q = searchField.getText();
                ObservableList<Skill> skills = DBSkill.lookupSkill(q);
                if (skills.size() == 0) {
                    int skillId = Integer.parseInt(q);
                    Skill s = DBSkill.lookupSkillById(skillId);
                    if (s != null) {
                        skills.add(s);
                    } else if (s == null) {
                        throw new Exception("No results found with that ID.");
                    }
                }
                availableSkillsTable.setItems(skills);

            } catch (NumberFormatException excpt) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText("We couldn't find a name matching that entry. \nPlease try again.");
                alert.showAndWait();
            } catch (Exception excpt) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setContentText(excpt.getMessage() + "\nPlease try again.");
                alert.showAndWait();
            }
        }
    }
}
