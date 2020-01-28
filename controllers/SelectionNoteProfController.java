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
    private AnchorPane anchorTeacherGrades;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private ComboBox<String> comboStudentGrade;

    @FXML
    private TableView<?> tableGradesStudent;

    @FXML
    private ComboBox<String> comboStudentNonattendance;

    @FXML
    private TableView<?> tableNonattendanceStudent;

    @FXML
    private TableView<?> tableCoursesSatisfaction;

    @FXML
    private TableView<?> tableStudentsGradesCourses;

    @FXML
    private Button newGradeButton;

    @FXML
    private Button newNonattendanceButton;

    ObservableList<TableStudentModule> olistStudents = FXCollections.observableArrayList();

    @FXML
    void newNonattendance(ActionEvent event)  throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/ajout_absence.fxml"));

        Scene sceneFromAnchor = anchorTeacherGrades.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void newGrade(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/ajout_note.fxml"));

        Scene sceneFromAnchor = anchorTeacherGrades.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void selectionNonattendanceStudent(ActionEvent event) {
        String value = comboStudentNonattendance.getValue();
        if(value != null) {
            System.out.println(value);
        }
    }

    @FXML
    void selectionStudentGrade(ActionEvent event) {
        String value = comboStudentGrade.getValue();
        if(value != null) {
            System.out.println(value);
        }

    }

    @FXML
    void quitFunction(ActionEvent event) {
        fromAnchorClose(anchorTeacherGrades);

    }

    @FXML
    void backFunction(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_liste_modules.fxml"));

        Scene sceneFromAnchor = anchorTeacherGrades.getScene();
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
        this.setData(comboStudentNonattendance);
        this.setData(comboStudentGrade);
        this.olistStudents = fillStudents(this.olistStudents);
    }

    public ObservableList<TableStudentModule> fillStudents(ObservableList<TableStudentModule> obl){
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
