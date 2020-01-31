package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import tables.TableGradesModule;
import tables.TableModuleAbsence;
import tables.TableStudentModule;
import tools.Tool;
import tools.Stockage;
import java.util.ArrayList;
import user.Administration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import tables.TableProfSatisfaction;

/**
 * 
 * This class contains all the methods which are linked with the main view for Administration
 * @author Alex JOBARD
 * @author Adam RIVIÈRE
 * 
 */

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

    /*
    *
    * ComboBoxes
    *
    */
    @FXML
    private ComboBox<String> comboGradesStudent;
    @FXML
    private ComboBox<String> comboStudentsNonattendance;
    
    /*
    *
    * Columns for table of grades from a student
    *
    */
    @FXML
    private TableView<TableGradesModule> tableGradesStudent;
    @FXML
    private TableColumn<TableGradesModule, String> testColumn;
    @FXML
    private TableColumn<TableGradesModule, String> gradeColumn;

    /*
    *
    * Table from nonattendance from a student
    *
    */
    @FXML
    private TableView<TableModuleAbsence> tableNonattendanceStudent;
    @FXML
    private TableColumn<TableModuleAbsence, String> dateColumn;
    @FXML
    private TableColumn<TableModuleAbsence, String> justificationColumn;

    /*
    *
    * Columns for tests from a student
    *
    */
    @FXML
    private TableView<TableStudentModule> tableGradesStudentsCourses;
    @FXML
    private TableColumn<TableStudentModule, String> nameColumn;
    @FXML
    private TableColumn<TableStudentModule, String> surnameColumn;
    @FXML
    private TableColumn<TableStudentModule, String> gradeModuleColumn;
    @FXML
    private TableColumn<TableStudentModule, String> idColumn;

    /*
    *
    * Columns for table satisfaction from module
    *
    */
    @FXML
    private TableView<TableProfSatisfaction> tableSatisfactionCourses;
    @FXML
    private TableColumn<TableProfSatisfaction, String> gradeSatisfactionColumn;
    @FXML
    private TableColumn<TableProfSatisfaction, String> commentaryColumn;

    /*
    *
    * ObservableLists
    *
    */
    ObservableList<TableProfSatisfaction> olistSatisfaction = FXCollections.observableArrayList();
    ObservableList<TableStudentModule> olistStudents = FXCollections.observableArrayList();
    ObservableList<TableGradesModule> olistTest = FXCollections.observableArrayList();
    ObservableList<TableModuleAbsence> olistAbsences = FXCollections.observableArrayList();

    /*
    *
    * Global value
    *
    */
    private String student;
    
    /**
     * Quits the app
     * @param event
     * @author Alex JOBARD
     */
    @FXML
    void quitFunction(ActionEvent event) {
        fromAnchorClose(anchorModuleStudentOffice);
    }

    /**
     * Goes back to the previous view
     * @param event
     * @throws Exception
     * @author Alex JOBARD
     */
    @FXML
    void backFunction(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_administration.fxml"));

        Scene sceneFromAnchor = anchorModuleStudentOffice.getScene();
        sceneFromAnchor.setRoot(pane);
    }
    
    /**
     * Triggers the comboGradeStudent comboBox
     * @param event
     * @author Alex JOBARD
     */
    @FXML
    void selectionGradesStudent(ActionEvent event) {
        String value = comboGradesStudent.getValue();
        if(value != null) {
            comboStudentsNonattendance.setValue(value);
            this.student = value;
            Stockage.setLoginStudent(Tool.getLogin(student));
            this.olistTest = fillTest(this.olistTest);
            tableGradesStudent.setItems(this.olistTest);

        }
    }

    /**
     * Triggers the comboStudentsNonAttendance comboBox
     * @param event
     * @author Alex JOBARD
     */
    @FXML
    void selectionStudentNonattendance(ActionEvent event) {
        String value = comboStudentsNonattendance.getValue();
        if(value != null) {
            comboGradesStudent.setValue(value);
            this.student = value;
            // this.createStudent();
            Stockage.setLoginStudent(Tool.getLogin(student));
            this.olistAbsences = fillAbsences(this.olistAbsences);
            this.tableNonattendanceStudent.setItems(this.olistAbsences);
        }
    }

    /**
     * Inits the comboBox
     * @param combobox
     * @author Alex JOBARD
     */
    public void setData(ComboBox<String> combobox){
        combobox.getItems().clear();
        combobox = this.fillStudents(combobox);
    }

    /**
     * Fills the comboBox with an array of students and returns it
     * @param comboBox ComboBox of String
     * @return comboBox
     * @author Adam RIVIERE
     */
    public ComboBox<String> fillStudents (ComboBox<String> comboBox){
        ArrayList<ArrayList<String>> array = ((Administration) Stockage.getPerson().getRole()).viewListAttendees();
        if (!array.isEmpty()){
            for (int i= 0; i< array.size(); i++){
                comboBox.getItems().add(Tool.stringForStudent(array.get(i).get(0),
                                                            array.get(i).get(1),
                                                            array.get(i).get(2)));
            }
        }
        return comboBox;
    }

    /**
     * Fills the satisfaction observableList and returns it
     * @param obl observableList of TableProfSatisfaction
     * @return obl observableList of TableProfSatisfaction
     * @author Adam RIVIERE
     */
    public ObservableList<TableProfSatisfaction> fillSatisfaction(ObservableList<TableProfSatisfaction> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Administration) Stockage.getPerson().getRole()).viewTableSatisfaction();
        for(int i =0; i<array.size(); i++){
            obl.add(new TableProfSatisfaction(array.get(i).get(0), array.get(i).get(1)));
        }
        return obl;
    }

    /**
     * Fills the students observableList and returns it
     * @param obl observableList of TableStudentModule
     * @return obl observableList of TableStudentModule
     * @author Adam RIVIERE
     */
    public ObservableList<TableStudentModule> fillStudents(ObservableList<TableStudentModule> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Administration) Stockage.getPerson().getRole()).viewTableAttendees();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableStudentModule( array.get(i).get(0),
                                    array.get(i).get(1),
                                    array.get(i).get(2),
                                    array.get(i).get(3)));
        }
        return obl;
    }

    /**
     * Inits all the colums
     * @author Alex JOBARD
     */
    private void initColumnsFactory(){
            //satisfaction
        gradeSatisfactionColumn.setCellValueFactory(new PropertyValueFactory<>("rate"));
        commentaryColumn.setCellValueFactory(new PropertyValueFactory<>("comments"));
            //grade students
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        testColumn.setCellValueFactory(new PropertyValueFactory<>("nameGrade"));
            //nonattendance
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        justificationColumn.setCellValueFactory(new PropertyValueFactory<>("justification"));
            //main table
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        gradeModuleColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
    }

    /**
     * Initialize the view
     * @author Alex JOBARD
     * @author Adam RIVIERE
     */
    @FXML
    void initialize() {
        initColumnsFactory();
        ((Administration) Stockage.getPerson().getRole()).createStudentsInModule();
        this.setData(comboStudentsNonattendance);
        this.setData(comboGradesStudent);
        this.olistStudents = this.fillStudents(this.olistStudents);
        tableGradesStudentsCourses.setItems(this.olistStudents);
        this.olistSatisfaction = this.fillSatisfaction(this.olistSatisfaction);
        tableSatisfactionCourses.setItems(this.olistSatisfaction);
    }

    /**
     * fills the grades observableList and returns it
     * @param obl observableList of TableGradesModule
     * @return obl observableList of TableGradesModule
     * @author Adam RIVIERE
     */
    public ObservableList<TableGradesModule> fillTest(ObservableList<TableGradesModule> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Administration) Stockage.getPerson().getRole()).viewMarks();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableGradesModule(array.get(i).get(0),
                                    array.get(i).get(1)));
        }
        return obl;
    }

    /**
     * fills the grades observableList and returns it
     * @param obl observableList of TableAbsencesModule
     * @return obl observableList of TableAbsencesModule
     * @author Adam RIVIERE
     */
    public ObservableList<TableModuleAbsence> fillAbsences (ObservableList<TableModuleAbsence> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Administration) Stockage.getPerson().getRole()).viewAbsencesStudent();
        for(int i = 0; i<array.size(); i++){
            obl.add(new TableModuleAbsence(array.get(i).get(0), array.get(i).get(1)));
        }
        return obl;
    }
}
