package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class SelectionNoteProfController extends ControllerAbs {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorProfNotes;

    @FXML
    private MenuItem retourMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private ComboBox<String> comboEleveNote;

    @FXML
    private TableView<?> tableNotesEleve;

    @FXML
    private ComboBox<String> comboEleveAbsence;

    @FXML
    private TableView<?> tableAbsencesEleve;

    @FXML
    private TableView<?> tableSatisfactionCour;

    @FXML
    private TableView<?> tableNotesElevesCour;

    @FXML
    private Button nouvelleNoteButton;

    @FXML
    void nouvelleAbsence(ActionEvent event)  throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ajout_absence.fxml"));

        Scene sceneFromAnchor = anchorProfNotes.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void nouvelleNote(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ajout_note.fxml"));

        Scene sceneFromAnchor = anchorProfNotes.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void selectionEleveAbsence(ActionEvent event) {
        String value = comboEleveAbsence.getValue();
        if(value != null) {
            System.out.println(value);
        }
    }

    @FXML
    void selectionEleveNote(ActionEvent event) {
        String value = comboEleveNote.getValue();
        if(value != null) {
            System.out.println(value);
        }

    }

    @FXML
    void fonctionQuit(ActionEvent event) {
        fromAnchorClose(anchorProfNotes);

    }

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_liste_modules.fxml"));

        Scene sceneFromAnchor = anchorProfNotes.getScene();
        sceneFromAnchor.setRoot(pane);

    }
    public void setData(ComboBox<String> combobox){
        combobox.getItems().clear();

        combobox.getItems().addAll(
                "Eleve 1 ",
                "Eleve 2",
                "Eleve 3",
                "Eleve 4",
                "Eleve 5");
    }

    @FXML
    void initialize() {
    this.setData(comboEleveAbsence);
    this.setData(comboEleveNote);

    }
}
