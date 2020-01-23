package sample;

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
    private AnchorPane anchorAjoutAbsence;

    @FXML
    private MenuItem retourMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private Button ajouterAbsenceButton;

    @FXML
    private DatePicker dateAbsencePicker;

    @FXML
    private ComboBox<String> comboEleveAbsence;

    @FXML
    private ComboBox<String> comboModuleAbsence;

    @FXML
    private ComboBox<String> heureDebutCombo;

    @FXML
    private ComboBox<String> minuteDebutCombo;

    @FXML
    private ComboBox<String> heureFinCombo;

    @FXML
    private ComboBox<String> minuteFinCombo;

    @FXML
    void ajouterAbsence(ActionEvent event) {

    }

    @FXML
    void fonctionQuit(ActionEvent event) {
        fromAnchorClose(anchorAjoutAbsence);
    }

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_prof_selection_note.fxml"));

        Scene sceneFromAnchor = anchorAjoutAbsence.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void selectionDate(ActionEvent event) {


    }

    @FXML
    void selectionEleveAbsence(ActionEvent event) {
        String value = comboEleveAbsence.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionHeureDebut(ActionEvent event) {
        String value = heureDebutCombo.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionHeureFin(ActionEvent event) {
        String value = heureFinCombo.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionMinuteDebut(ActionEvent event) {
        String value = minuteDebutCombo.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionMinuteFin(ActionEvent event) {
        String value = minuteFinCombo.getValue();
        if (value != null) {
            System.out.println(value);
        }


    }

    @FXML
    void selectionModuleAbsence(ActionEvent event) {
        String value = comboModuleAbsence.getValue();
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

    public void setHeure(ComboBox<String> combobox) {
        combobox.getItems().clear();
        int i;
        for (i = 0; i < 24; i++) {
            combobox.getItems().add(Integer.toString(i));
        }

    }

    public void setModule() {


        comboModuleAbsence.getItems().clear();

        comboModuleAbsence.getItems().addAll(
                "Module1 ",
                "Module 2",
                "Module 3",
                "Module 4",
                "Module 5");


}

public void setEleve(){

    comboEleveAbsence.getItems().clear();

    comboEleveAbsence.getItems().addAll(
            "eleve 1",
            "eleve 2",
            "eleve 3",
            "eleve 4",
            "eleve 5");

}


    @FXML
    void initialize() {
        setMinute(minuteDebutCombo);
        setMinute(minuteFinCombo);
        setHeure(heureDebutCombo);
        setHeure(heureFinCombo);
        setEleve();
        setModule();

    }
}
