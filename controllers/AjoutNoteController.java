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
    private AnchorPane anchorAddGrade;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private TextField nameGradeField;

    @FXML
    private TextField coefficientField;

    @FXML
    private TableView<?> tableNewGrades;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> surnameColumn;

    @FXML
    private TableColumn<?, ?> gradeColumn;

    @FXML
    private TextField nameStudentField;

    @FXML
    private TextField surnameStudentField;

    @FXML
    private TextField gradeStudentField;

    @FXML
    private Button addGradeButton;

    @FXML
    private Button deleteButton;

    @FXML
    void addGrade(ActionEvent event) {

    }

    @FXML
    void quitFunction(ActionEvent event) {

        fromAnchorClose(anchorAddGrade);
    }

    @FXML
    void backFunction(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_prof_selection_note.fxml"));

        Scene sceneFromAnchor = anchorAddGrade.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    @FXML
    void deleteGrade(ActionEvent event) {

    }

    @FXML
    void initialize() {


    }
}
