package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AbsenceAdministrationController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorAbsenceAdmin;

    @FXML
    private TableView<?> tableAbsences;

    @FXML
    private CheckBox justificationCheckBox;

    @FXML
    private TextField etudantTextField;

    @FXML
    private TextField moduleTextField;

    @FXML
    private TextField dateTextField;

    @FXML
    void justificationAbsence(ActionEvent event) {

    }

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_administration.fxml"));

        Scene sceneFromAnchor = anchorAbsenceAdmin.getScene();
        sceneFromAnchor.setRoot(pane);



    }

    @FXML
    void fonctionQuit(ActionEvent event) {
        fromAnchorClose(anchorAbsenceAdmin);
    }

    @FXML
    void initialize() {

    }
}
