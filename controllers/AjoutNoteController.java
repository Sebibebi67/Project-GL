package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AjoutNoteController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorAjoutNote;

    @FXML
    private MenuItem retourMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private TextField nomNoteField;

    @FXML
    private TextField coefficientField;

    @FXML
    private TableView<?> tableNouvellesNotes;

    @FXML
    private TableColumn<?, ?> nomColumn;

    @FXML
    private TableColumn<?, ?> prenomColumn;

    @FXML
    private TableColumn<?, ?> noteColumn;

    @FXML
    private TextField nomEleveField;

    @FXML
    private TextField prenomEleveField;

    @FXML
    private TextField noteEleveField;

    @FXML
    private Button ajouterNoteButton;

    @FXML
    private Button supprimerButton;

    @FXML
    void ajouterNote(ActionEvent event) {

    }

    @FXML
    void fonctionQuit(ActionEvent event) {

        fromAnchorClose(anchorAjoutNote);
    }

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_prof_selection_note.fxml"));

        Scene sceneFromAnchor = anchorAjoutNote.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    @FXML
    void supprimerNote(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }
}
