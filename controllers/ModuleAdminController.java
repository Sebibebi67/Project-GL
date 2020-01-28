package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ModuleAdminController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorModuleStudentOffice;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private ComboBox<String> comboGradesStudent;

    @FXML
    private TableView<?> tableGradesStudent;

    @FXML
    private ComboBox<String> comboStudentsNonattendance;

    @FXML
    private TableView<?> tableNonattendanceStudent;

    @FXML
    private TableView<?> tableSatisfactionCourses;

    @FXML
    private TableView<?> tableGradesStudentsCourses;

    @FXML
    void quitFunction(ActionEvent event) {

        fromAnchorClose(anchorModuleStudentOffice);
    }

    @FXML
    void backFunction(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_administration.fxml"));

        Scene sceneFromAnchor = anchorModuleStudentOffice.getScene();
        sceneFromAnchor.setRoot(pane);


    }

    @FXML
    void selectionGradesStudent(ActionEvent event) {

    }

    @FXML
    void selectionStudentNonattendance(ActionEvent event) {

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
    this.setData(comboStudentsNonattendance);
    this.setData(comboGradesStudent);

    }
}
