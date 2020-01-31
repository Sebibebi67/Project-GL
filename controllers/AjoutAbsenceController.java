package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import tools.Tool;

import tools.Query;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import tools.Stockage;
import user.Professor;

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

    String module = new String("");

    @FXML
    void addNonattendance(ActionEvent event) {
        String moduleValue = comboModuleNonattendance.getValue();
        String studentValue = comboNonattendanceStudent.getValue();
        String startingHour = startingHourCombo.getValue();
        String startingMinute = startingMinuteCombo.getValue();
        String endingHour = endingHourCombo.getValue();
        String endingMinute = endingMinuteFinCombo.getValue();
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
        module = comboModuleNonattendance.getValue();
        if(!module.equalsIgnoreCase("")) {
            setStudent();
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
        ArrayList<String> array = ((Professor) Stockage.getPerson().getRole()).viewListModules();
        for (int i = 0; i< array.size(); i++){
            comboModuleNonattendance.getItems().add(array.get(i));
        }
        comboModuleNonattendance.setEditable(false);
    }

public void setStudent(){/////////////////////////////////////////////////////////////////////////////////////////////
    comboNonattendanceStudent.getItems().clear();
    ArrayList<ArrayList<String>> array = Query.attendees(module);
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
                comboNonattendanceStudent.getItems().add(Tool.stringForStudent(students.get(i).get(0),
                                                                students.get(i).get(1),
                                                                students.get(i).get(2)));
            }
        }
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
