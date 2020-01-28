package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import tables.TableStudentModule;
import tools.Stockage;
import user.Professor;

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

    ObservableList<TableStudentModule> olistStudents = FXCollections.observableArrayList();

    @FXML
    void nouvelleAbsence(ActionEvent event)  throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/ajout_absence.fxml"));

        Scene sceneFromAnchor = anchorProfNotes.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void nouvelleNote(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/ajout_note.fxml"));

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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_liste_modules.fxml"));

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
        this.olistStudents = feelStudents(this.olistStudents);
    }

    public ObservableList<TableStudentModule> feelStudents(ObservableList<TableStudentModule> obl){
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewTableAttendees();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableStudentModule( array.get(i).get(0),
                                    array.get(i).get(1),
                                    array.get(i).get(2),
                                    array.get(i).get(3)));
        }

        return obl;
    }
}
