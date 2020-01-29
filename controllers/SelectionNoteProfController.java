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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tables.TableStudentModule;
import tools.Stockage;
import tools.Tool;
import user.Professor;

public class SelectionNoteProfController extends ControllerAbs {

    //column for table with all students and their grades
    @FXML
    private TableColumn<TableStudentModule, String> nameColumn;

    @FXML
    private TableColumn<TableStudentModule, String> surnameColumn;

    @FXML
    private TableColumn<TableStudentModule, String> gradeColumn;

    @FXML
    private TableColumn<TableStudentModule, String> idColumn;

    //column for table in the tab Students grade

    @FXML
    private TableColumn<?, ?> testColumn;

    @FXML
    private TableColumn<?, ?> gradeStudentColumn;

    //column for table in the satisfaction tab
    @FXML
    private TableColumn<?, ?> gradeSatisfactionColumn;

    @FXML
    private TableColumn<?, ?> commentaryColumn;

    //column for table in nonattendance table
    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> justificationColumn;

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
    private TableView<TableStudentModule> tableStudentsGradesCourses;

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

        combobox = this.fillStudents(combobox);
    }

    private void initColumnCellFactory(){
        //column for table with all students and their grades
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("login"));


    }

    @FXML
    void initialize() {

        this.initColumnCellFactory();

        ((Professor) Stockage.getPerson().getRole()).createListStudent(Stockage.getActiveModule().getName());
        
        this.setData(comboStudentNonattendance);
        this.setData(comboStudentGrade);
        this.olistStudents = this.fillStudents(this.olistStudents);
        tableStudentsGradesCourses.setItems(this.olistStudents);
    }

    public ObservableList<TableStudentModule> fillStudents(ObservableList<TableStudentModule> obl){
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewTableAttendees();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableStudentModule( array.get(i).get(0),
                                    array.get(i).get(1),
                                    array.get(i).get(2),
                                    array.get(i).get(3)));
            System.out.println(i);
            System.out.println(obl.get(i).getName());
        }

        return obl;
    }

    public ComboBox<String> fillStudents (ComboBox<String> comboBox){
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewListAttendees();
        if (!array.isEmpty()){
            for (int i= 0; i< array.size(); i++){
                comboBox.getItems().add(Tool.stringForStudent(array.get(i).get(0),
                                                            array.get(i).get(1), array.get(i).get(2)));
            }
        }
        return comboBox;
    }
}

