package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tables.TableNonattendance;


public class AbsenceAdministrationController extends ControllerAbs{

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorNonattendanceStudentOffice;

    @FXML
    private TableView<TableNonattendance> tableNonattendance;

    @FXML
    private CheckBox justificationCheckBox;

    @FXML
    private TextField studentTextField;

    @FXML
    private TextField moduleTextField;

    @FXML
    private TextField dateTextField;

    @FXML
    void nonattendanceJustification(ActionEvent event) {

    }

    @FXML
    void backFunction(ActionEvent event) throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_administration.fxml"));

        Scene sceneFromAnchor = anchorNonattendanceStudentOffice.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    public void fillFields(javafx.scene.input.MouseEvent mouseEvent) {
        TableNonattendance tablerow = tableNonattendance.getSelectionModel().getSelectedItem();
        if (tablerow != null){
            String strName = tablerow.getName();
            String strSurname = tablerow.getSurname();
            String strId = tablerow.getId();

            studentTextField.setText(strName+","+strSurname+","+strId);
            moduleTextField.setText(tablerow.getModule());
            dateTextField.setText(tablerow.getDate());
            if(tablerow.getJustification() == "Oui") {
                justificationCheckBox.setSelected(true);
            }
            else{
                justificationCheckBox.setSelected(false);
            }
        }
    }

    @FXML
    void quitFunction(ActionEvent event) {
        fromAnchorClose(anchorNonattendanceStudentOffice);
    }

    @FXML
    void initialize() {

    }
}
