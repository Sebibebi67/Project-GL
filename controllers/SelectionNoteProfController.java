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
import tables.TableModuleAbsence;
import tables.TableProfSatisfaction;
import tables.TableProfTest;
import tables.TableStudentModule;
import tools.Stockage;
import tools.Tool;
import user.Professor;
import user.Student;

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
    private TableColumn<TableProfTest, String> testColumn;

    @FXML
    private TableColumn<TableProfTest, String> gradeStudentColumn;

    //column for table in the satisfaction tab
    @FXML
    private TableColumn<TableProfSatisfaction, String> gradeSatisfactionColumn;

    @FXML
    private TableColumn<TableProfSatisfaction, String> commentaryColumn;

    //column for table in nonattendance table
    @FXML
    private TableColumn<TableModuleAbsence, String> dateColumn;

    @FXML
    private TableColumn<TableModuleAbsence, String> justificationColumn;

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
    private TableView<TableProfTest> tableGradesStudent;

    @FXML
    private ComboBox<String> comboStudentNonattendance;

    @FXML
    private TableView<TableModuleAbsence> tableNonattendanceStudent;

    @FXML
    private TableView<TableProfSatisfaction> tableCoursesSatisfaction;

    @FXML
    private TableView<TableStudentModule> tableStudentsGradesCourses;

    @FXML
    private Button newGradeButton;

    @FXML
    private Button newNonattendanceButton;

    ObservableList<TableStudentModule> olistStudents = FXCollections.observableArrayList();

    ObservableList<TableProfTest> olistTest = FXCollections.observableArrayList();

    ObservableList<TableModuleAbsence> olistAbsences = FXCollections.observableArrayList();

    ObservableList<TableProfSatisfaction> olistSatisfaction = FXCollections.observableArrayList();

    private String student;

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
            comboStudentGrade.setValue(value);
            this.student = value;
            this.createStudent();
            this.olistAbsences = fillAbsences(this.olistAbsences);
            this.tableNonattendanceStudent.setItems(this.olistAbsences);
        }
    }

    @FXML
    void selectionStudentGrade(ActionEvent event) {
        String value = comboStudentGrade.getValue();
        if(value != null) {
            comboStudentNonattendance.setValue(value);
            this.student = value;
            this.createStudent();
            this.olistTest = fillTest(this.olistTest);
            tableGradesStudent.setItems(this.olistTest);

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

        //column for table with all tests from a student
        testColumn.setCellValueFactory(new PropertyValueFactory<>("test"));
        gradeStudentColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        //column for table with all the absences from a student
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        justificationColumn.setCellValueFactory(new PropertyValueFactory<>("justification"));

        //column for table with all the satisfaction from a student
        commentaryColumn.setCellValueFactory(new PropertyValueFactory<>("comments"));
        gradeSatisfactionColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));



    }

    @FXML
    void initialize() {
        this.initColumnCellFactory();
        ((Professor) Stockage.getPerson().getRole()).createListStudent(Stockage.getActiveModule().getName());
        this.setData(comboStudentNonattendance);
        this.setData(comboStudentGrade);
        this.olistStudents = this.fillStudents(this.olistStudents);
        tableStudentsGradesCourses.setItems(this.olistStudents);
        this.olistSatisfaction = this.fillSatisfaction(this.olistSatisfaction);
        tableCoursesSatisfaction.setItems(this.olistSatisfaction);
    }

    public ObservableList<TableStudentModule> fillStudents(ObservableList<TableStudentModule> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewTableAttendees();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableStudentModule( array.get(i).get(0),
                                    array.get(i).get(1),
                                    array.get(i).get(2),
                                    array.get(i).get(3)));
        }

        return obl;
    }

    public ComboBox<String> fillStudents (ComboBox<String> comboBox){
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewListAttendees();
        if (!array.isEmpty()){
            for (int i= 0; i< array.size(); i++){
                comboBox.getItems().add(Tool.stringForStudent(array.get(i).get(0),
                                                            array.get(i).get(1),
                                                            array.get(i).get(2)));
            }
        }
        return comboBox;
    }

    public ObservableList<TableProfTest> fillTest(ObservableList<TableProfTest> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewMarksAttendee(Stockage.getActiveModule().getName(), Tool.getLogin(this.student));
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableProfTest(array.get(i).get(0),
                                    array.get(i).get(1)));
        }
        return obl;
    }

    public ObservableList<TableModuleAbsence> fillAbsences (ObservableList<TableModuleAbsence> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewTableAbsences();
        for(int i = 0; i<array.size(); i++){
            obl.add(new TableModuleAbsence(array.get(i).get(0), array.get(i).get(1)));
        }
        return obl;
    }

    public void createStudent(){
        if (this.student!=null){
            Stockage.setStudent(new Student(Tool.getLogin(student)));
        }
    }

    public ObservableList<TableProfSatisfaction> fillSatisfaction(ObservableList<TableProfSatisfaction> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewTableSatisfaction();
        for(int i =0; i<array.size(); i++){
            obl.add(new TableProfSatisfaction(array.get(i).get(0), array.get(i).get(1)));
        }
        return obl;
    }
}

