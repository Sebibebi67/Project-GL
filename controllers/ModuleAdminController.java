package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import tables.TableAbsencesModule;
import tables.TableGradesModule;
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
    private ComboBox<String> comboStudentsNonattendance;

    //columns for table of grades from a student
    @FXML
    private TableView<TableGradesModule> tableGradesStudent;

    @FXML
    private TableColumn<TableGradesModule, String> testColumn;

    @FXML
    private TableColumn<TableGradesModule, String> gradeColumn;

    //table from nonattendance from a student
    @FXML
    private TableView<TableAbsencesModule> tableNonattendanceStudent;
    @FXML
    private TableColumn<TableAbsencesModule, String> dateColumn;

    @FXML
    private TableColumn<TableAbsencesModule, String> justificationColumn;


    //columns for tests from a student
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

    //columns for table satisfaction from module
    @FXML
    private TableView<TableProfSatisfaction> tableSatisfactionCourses;

    ObservableList<TableProfSatisfaction> olistSatisfaction = FXCollections.observableArrayList();

    ObservableList<TableStudentModule> olistStudents = FXCollections.observableArrayList();

    @FXML
    private TableColumn<TableProfSatisfaction, String> gradeSatisfactionColumn;

    @FXML
    private TableColumn<TableProfSatisfaction, String> commentaryColumn;

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
        combobox = this.fillStudents(combobox);
    }

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

    public ObservableList<TableProfSatisfaction> fillSatisfaction(ObservableList<TableProfSatisfaction> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Administration) Stockage.getPerson().getRole()).viewTableSatisfaction();
        for(int i =0; i<array.size(); i++){
            obl.add(new TableProfSatisfaction(array.get(i).get(0), array.get(i).get(1)));
        }
        return obl;
    }

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
}
