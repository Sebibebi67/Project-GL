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
    private AnchorPane anchorMenuAdministration;

    @FXML
    private MenuItem retourMenu;

    @FXML
    private MenuItem quiitMenu;

    @FXML
    private TextField delaisAbsence;

    @FXML
    private TextField delaisNotesField;

    @FXML
    private Button absencesButton;

    @FXML
    private Button modulesButton;

    @FXML
    private Button etudiantsButton;

    @FXML
    void fonctionQuit(ActionEvent event) {
        fromAnchorClose(anchorMenuAdministration);
    }

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene sceneFromAnchor = anchorMenuAdministration.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    @FXML
    void modifierDelaisAbsence(ActionEvent event) {
        System.out.println(delaisAbsence.getText());
    }

    @FXML
    void modifierDelaisNotes(ActionEvent event) {
    System.out.println(delaisNotesField.getText());
    }

    @FXML
    void selectionAbsences(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_absences_administration.fxml"));

        Scene sceneFromAnchor = anchorMenuAdministration.getScene();
        sceneFromAnchor.setRoot(pane);



    }

    @FXML
    void selectionEtudiants(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_adminstration_bulletin.fxml"));

        Scene sceneFromAnchor = anchorMenuAdministration.getScene();
        sceneFromAnchor.setRoot(pane);


    }

    @FXML
    void selectionModules(ActionEvent event) throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_admin_modules.fxml"));

        Scene sceneFromAnchor = anchorMenuAdministration.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    public void setDelays(){
        delaisAbsence.setText("1000");
        delaisNotesField.setText("2");


    }

    @FXML
    void initialize() {
        this.setDelays();

    }
}
