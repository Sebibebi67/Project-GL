package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ModuleAdminController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorModuleAdmin;

    @FXML
    private MenuItem retourMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private ComboBox<String> comboEleveNotes;

    @FXML
    private TableView<?> tableNotesEleve;

    @FXML
    private ComboBox<String> comboEleveAbsences;

    @FXML
    private TableView<?> tableAbsencesEleve;

    @FXML
    private TableView<?> tableSatisfactionCour;

    @FXML
    private TableView<?> tableNotesElevesCour;

    @FXML
    void fonctionQuit(ActionEvent event) {

        fromAnchorClose(anchorModuleAdmin);
    }

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_administration.fxml"));

        Scene sceneFromAnchor = anchorModuleAdmin.getScene();
        sceneFromAnchor.setRoot(pane);


    }

    @FXML
    void selectionEleveNotes(ActionEvent event) {

    }

    @FXML
    void selectionElevesAbsence(ActionEvent event) {

    }

    public void setData(ComboBox<String> combobox){
        combobox.getItems().clear();

        combobox.getItems().addAll(
                "Eleve 1 ",
                "Eleve 2",
                "Eleve 3",
                "Eleve 4",
                "Eleve 5");
    }

    @FXML
    void initialize() {
    this.setData(comboEleveAbsences);
    this.setData(comboEleveNotes);

    }
}
