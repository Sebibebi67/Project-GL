package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

    @FXML
    private TableView<?> tableAverageGradeStudent;

    @FXML
    private TableView<?> tableNonattendanceStudent;

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
    void generateReportFunction(ActionEvent event) {

    }

    @FXML
    void getHelpToJury(ActionEvent event) {

    }

    @FXML
    void selectionStudent(ActionEvent event) {

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
    void selectionYearGroup(ActionEvent event) {
        yearGroup = yearGroupCombo.getValue();
        if (yearGroup != null) {
            setComboCourses();
        }

    }

    @FXML
    void DetailReportFunction(ActionEvent event) {

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
