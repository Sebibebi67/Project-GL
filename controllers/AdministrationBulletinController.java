package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import study.Course;
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
    void generateReportFunction(ActionEvent event)
    {
        String firstname = Tool.getFirstname(studentCombo.getValue());
        String surname = Tool.getSurname(studentCombo.getValue());
        Course course = Tool.getCourse(studentCombo.getValue());
        ((Student) student).getForm().generateReport();
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
        if (courses.equalsIgnoreCase("TS")) {
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 1");
        }
        else if(courses.equalsIgnoreCase("IPS")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 2","Eleve 3");
        }
        else if(courses.equalsIgnoreCase("INFO1")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 4");
        }
        else if(courses.equalsIgnoreCase("SNUM1")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 5","Eleve 6");
        }
        else if(courses.equalsIgnoreCase("PHOT1")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 7");
        }

        else if(courses.equalsIgnoreCase("IMR1")){
            studentCombo.getItems().clear();
            studentCombo.getItems().addAll("Eleve 666");
        }

        else if(courses.equalsIgnoreCase("INFO2")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 8");
        }
        else if(courses.equalsIgnoreCase("SNUM2")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 9","Eleve 10");
        }
        else if(courses.equalsIgnoreCase("PHOT2")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 11");
        }
        else if(courses.equalsIgnoreCase("IMR2")){
            studentCombo.getItems().clear();
            studentCombo.getItems().addAll("Eleve 777");
        }

        else if(courses.equalsIgnoreCase("INFO3")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 12");
        }
        else if(courses.equalsIgnoreCase("SNUM3")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 12","Eleve 13");
        }
        else if(courses.equalsIgnoreCase("PHOT3")){
            studentCombo.getItems().clear();

            studentCombo.getItems().addAll("Eleve 14");
        }
        else if(courses.equalsIgnoreCase("IMR3")){
            studentCombo.getItems().clear();
            studentCombo.getItems().addAll("Eleve 888");
        }
        else{

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
    public void detailedReportFunction(ActionEvent event) 
    {

    }

    public void setComboYearGroup(){
        yearGroupCombo.getItems().clear();

        yearGroupCombo.getItems().addAll(
                "2019-2022",
                "2020-2023",
                "2021-2024");
    }
    public void setComboCourses() {
        if (yearGroup.equalsIgnoreCase("2019-2022")) {
            coursesCombo.getItems().clear();

            coursesCombo.getItems().addAll(
                    "TS",
                    "IPS",
                    "IMR1",
                    "INFO1",
                    "PHOTO1",
                    "SNUM1");
        }
        else if(yearGroup.equalsIgnoreCase("2020-2023")){
            coursesCombo.getItems().clear();

            coursesCombo.getItems().addAll(
                    "IMR2",
                    "INFO2",
                    "PHOTO2",
                    "SNUM2");
        }
        else if(yearGroup.equalsIgnoreCase("2021-2024")){
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
