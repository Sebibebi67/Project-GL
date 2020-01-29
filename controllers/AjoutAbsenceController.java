package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class AjoutAbsenceController extends ControllerAbs {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorAddNonattendance;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private Button addNonattendanceButton;

    @FXML
    private DatePicker dateNonattendancePicker;

    @FXML
    private ComboBox<String> comboNonattendanceStudent;

    @FXML
    private ComboBox<String> comboModuleNonattendance;

    @FXML
    private ComboBox<String> startingHourCombo;

    @FXML
    private ComboBox<String> startingMinuteCombo;

    @FXML
    private ComboBox<String> endingHourCombo;

    @FXML
    private ComboBox<String> endingMinuteFinCombo;

    @FXML
    void addNonattendance(ActionEvent event) {

    }

    @FXML
    void quitFunction(ActionEvent event) {
        fromAnchorClose(anchorAddNonattendance);
    }

    @FXML
    void backFunction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_prof_selection_note.fxml"));

        Scene sceneFromAnchor = anchorAddNonattendance.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void selectionDate(ActionEvent event) {


    }

    @FXML
    void selectionStudentNonattendance(ActionEvent event) {
        String value = comboNonattendanceStudent.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionStartingHour(ActionEvent event) {
        String value = startingHourCombo.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionEndingHour(ActionEvent event) {
        String value = endingHourCombo.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionStartingMinute(ActionEvent event) {
        String value = startingMinuteCombo.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionEndingMinute(ActionEvent event) {
        String value = endingMinuteFinCombo.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionModulenonattendance(ActionEvent event) {
        String value = comboModuleNonattendance.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    public void setMinute(ComboBox<String> combobox) {
        combobox.getItems().clear();
        int i;
        for (i = 0; i < 60; i++) {
            combobox.getItems().add(Integer.toString(i));
        }
    }

    public void setHour(ComboBox<String> combobox) {
        combobox.getItems().clear();
        int i;
        for (i = 0; i < 24; i++) {
            combobox.getItems().add(Integer.toString(i));
        }

    }

    public void setModule() {


        comboModuleNonattendance.getItems().clear();

        comboModuleNonattendance.getItems().addAll(
                "Module1 ",
                "Module 2",
                "Module 3",
                "Module 4",
                "Module 5");


}

public void setStudent(){

    comboNonattendanceStudent.getItems().clear();

    comboNonattendanceStudent.getItems().addAll(
            "eleve 1",
            "eleve 2",
            "eleve 3",
            "eleve 4",
            "eleve 5");

}


    @FXML
    void initialize() {
        setMinute(startingMinuteCombo);
        setMinute(endingMinuteFinCombo);
        setHour(startingHourCombo);
        setHour(endingHourCombo);
        setStudent();
        setModule();

    }
}
