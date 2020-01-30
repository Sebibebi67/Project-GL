package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import tools.Query;
import tools.Tool;
import user.Person;
import user.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import tables.TableAbsencesModule;
import tables.TableAverageGradeStudent;

public class AdministrationBulletinController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorReport;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private ComboBox<String> yearGroupCombo;

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private ComboBox<String> studentCombo;

    //columns for tableAverageGradeStudent

    @FXML
    private TableView<TableAverageGradeStudent> tableAverageGradeStudent;

    @FXML
    private TableColumn<TableAverageGradeStudent, String> moduleColumn;

    @FXML
    private TableColumn<TableAverageGradeStudent, String> averageGradeColumn;

    @FXML
    private TableColumn<TableAverageGradeStudent, String> retakeColumn;

    //columns for tableNonattendanceStudent

    @FXML
    private TableView<TableAbsencesModule> tableNonattendanceStudent;

    @FXML
    private TableColumn<TableAbsencesModule, String> NonattendanceDateColumn;

    @FXML
    private TableColumn<TableAbsencesModule, String> justificationColumn;

    @FXML
    private Button generateReportButton;

    @FXML
    private Button detailButton;

    @FXML
    private TextField helpToJuryField;

    String courses = new String("");
    String student = new String("");
    String yearGroup = new String("");
    @FXML
    void quitFunction(ActionEvent event) {
    fromAnchorClose(anchorReport);
    }

    @FXML
    void backFunction(ActionEvent event) throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_administration.fxml"));

        Scene sceneFromAnchor = anchorReport.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    @FXML
    void generateReportFunction(ActionEvent event) throws IOException
    {
        String login = Tool.getLogin(studentCombo.getValue());
        String pswd = (String) Query.userData(login).get(1);
        Person student = new Person(login, pswd);
        ((Student) student.getRole()).getForm().generateReport(login, pswd, ((Student) student.getRole()).getCourse(), 0);
    }

    @FXML
    void getHelpToJury()
    {
        int fx = 0;
        // int abs = 0;
        String text;
        ArrayList<Double> avg = new ArrayList<>();
        ObservableList<TableAverageGradeStudent> obl = tableAverageGradeStudent.getItems();
        for (int i = 0; i< obl.size(); i++)
        {
            avg.add(Double.parseDouble(obl.get(i).getAverageGrade()));
        }
        for (int i = 0; i< avg.size(); i++)
        {
            if (avg.get(i) < 11) fx++;
        }
        if (fx == 0) text = "Passage automatique";
        else if (fx < 2) text = "Redoublement";
        else text = "Exclusion";
        helpToJuryField.setText(text);
    }

    @FXML
    void selectionStudent(ActionEvent event) {
        getHelpToJury();
    }

    @FXML
    void selectionCourses(ActionEvent event) {
        courses = coursesCombo.getValue();
        if(courses !=null) {
            setStudents();
        }
    }

    private void setStudents() {
        studentCombo.getItems().clear();
        ArrayList<ArrayList<String>> array = Query.allStudentsInCourse(courses);
        ArrayList<ArrayList<String>> students = new ArrayList<ArrayList<String>>();
        if(!array.isEmpty()){
            for(int i = 0;i < array.get(0).size();i++){
                ArrayList<String> student = new ArrayList<String>();
                student.add(array.get(0).get(i).toString());
                student.add(array.get(1).get(i).toString());
                student.add(array.get(2).get(i).toString());
                students.add(student);
            }
            for (int i= 0; i< students.size(); i++){
                studentCombo.getItems().add(Tool.stringForStudent(students.get(i).get(0),
                                                                students.get(i).get(1),
                                                                students.get(i).get(2)));
            }
        }
    }

    @FXML
    public void selectionYearGroup(ActionEvent event) {
        yearGroup = yearGroupCombo.getValue();
        if (yearGroup != null) {
            setComboCourses();
        }

    }

    @FXML
    public void detailedReportFunction(ActionEvent event)  throws IOException
    {
        String login = Tool.getLogin(studentCombo.getValue());
        String pswd = (String) Query.userData(login).get(1);
        Person student = new Person(login, pswd);
        ((Student) student.getRole()).getForm().generateReport(login, pswd, ((Student) student.getRole()).getCourse(), 1);
    }

    public void setComboYearGroup(){
        yearGroupCombo.getItems().clear();

        yearGroupCombo.getItems().addAll(
                "1ère Années",
                "2ème Années",
                "3ème Années");
    }
    public void setComboCourses() {
        if (yearGroup.equalsIgnoreCase("1ère Années")) {
            coursesCombo.getItems().clear();

            coursesCombo.getItems().addAll(
                    "TS",
                    "IPS",
                    "IMR1",
                    "INFO1",
                    "PHOT1",
                    "SNUM1");
        }
        else if(yearGroup.equalsIgnoreCase("2ème Années")){
            coursesCombo.getItems().clear();

            coursesCombo.getItems().addAll(
                    "IMR2",
                    "INFO2",
                    "PHOTO2",
                    "SNUM2");
        }
        else if(yearGroup.equalsIgnoreCase("3ème Années")){
            coursesCombo.getItems().clear();

            coursesCombo.getItems().addAll(
                    "IMR3",
                    "INFO3",
                    "PHOTO3",
                    "SNUM3");
        }
        else{

        }
    }

    @FXML
    void initialize() {
        this.setComboYearGroup();


    }
}
