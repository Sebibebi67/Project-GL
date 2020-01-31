package controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MenuAdministrationController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorStudentOfficeMenu;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private TextField delayNonattendance;

    @FXML
    private TextField delayGradesField;

    @FXML
    private Button nonattendanceButton;

    @FXML
    private Button modulesButton;

    @FXML
    private Button studentsButton;

    @FXML
    void quitFunction(ActionEvent event) {
        fromAnchorClose(anchorStudentOfficeMenu);
    }

    @FXML
    void backFunction(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/login.fxml"));

        Scene sceneFromAnchor = anchorStudentOfficeMenu.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void changeDelayNonattendance(ActionEvent event) {
        System.out.println(delayNonattendance.getText());
    }

    @FXML
    void changeDelayGrades(ActionEvent event) {
    System.out.println(delayGradesField.getText());
    }

    @FXML
    /**
     * Opens the absence manager window for administrators.
     * @author Alex JOBARD
     * @throws Exception
     */
    void selectionNonattendance(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_absences_administration.fxml"));

        Scene sceneFromAnchor = anchorStudentOfficeMenu.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    @FXML
    /**
     * Opens the students manager window for administrators.
     * @author Alex JOBARD
     * @throws Exception
     */
    void selectionStudents(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_adminstration_bulletin.fxml"));

        Scene sceneFromAnchor = anchorStudentOfficeMenu.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    @FXML
    /**
     * Opens the modules manager window for administrators.
     * @author Alex JOBARD
     * @throws Exception
     */
    void selectionModules(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/moduleList_ViewStudentOffice.fxml"));

        Scene sceneFromAnchor = anchorStudentOfficeMenu.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    public void setDelays(){
        delayNonattendance.setText("1000");
        delayGradesField.setText("2");
    }

    @FXML
    void initialize() {
        this.setDelays();
    }
}
