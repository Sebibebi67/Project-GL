package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tables.TableNonattendance;
import tables.TableStudentModule;


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

    //columns for table of Non attendance

    @FXML
    private TableView<TableNonattendance> tableNonattendance;

    @FXML
    private TableColumn<TableNonattendance, String> nameColumn;

    @FXML
    private TableColumn<TableNonattendance, String> surnameColumn;

    @FXML
    private TableColumn<TableNonattendance, String> idColumn;

    @FXML
    private TableColumn<TableNonattendance, String> moduleColumn;

    @FXML
    private TableColumn<TableNonattendance, String> dateColumn;

    @FXML
    private TableColumn<TableNonattendance, String> justificationColumn;

    @FXML
    private CheckBox justificationCheckBox;

    @FXML
    private TextField studentTextField;

    @FXML
    private TextField moduleTextField;

    @FXML
    private TextField dateTextField;

    private ObservableList<TableNonattendance> olistNonattendance = FXCollections.observableArrayList();

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

    public void initCellFactoryColumn() {
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        justificationColumn.setCellValueFactory(new PropertyValueFactory<>("justification"));
    }

    @FXML
    void initialize(){
        initCellFactoryColumn();
        olistNonattendance.add(new TableNonattendance("nom", "prenom", "id", "module", "22-12-2019", "Oui"));
        olistNonattendance.add(new TableNonattendance("nom2", "prenom2", "id2", "module2", "22-12-2019", "Non"));
        tableNonattendance.setItems(olistNonattendance);

    }
}
