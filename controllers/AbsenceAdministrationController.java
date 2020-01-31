package controllers;

import java.net.URL;
import java.util.ArrayList;
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
// import tables.TableStudentModule;
import tools.Stockage;
import tools.Tool;
import user.Administration;


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
    /**
     * Registers the justification of an absence.
     * @author Alex JOBARD
     */
    void nonattendanceJustification(ActionEvent event) {
        String student = Tool.getLogin(studentTextField.getText());
        String module = moduleTextField.getText();
        String date = dateTextField.getText();
        ((Administration) Stockage.getPerson().getRole()).updateJustification(student, module, date);
        this.olistNonattendance = fillAbsences(olistNonattendance);
        tableNonattendance.setItems(olistNonattendance);
    }

    @FXML
    void backFunction(ActionEvent event) throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_administration.fxml"));

        Scene sceneFromAnchor = anchorNonattendanceStudentOffice.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    /**
     * Prints the information of the selected absence in the right text fields.
     * @author Alex JOBARD
     */
    public void fillFields(javafx.scene.input.MouseEvent mouseEvent) {
        TableNonattendance tablerow = tableNonattendance.getSelectionModel().getSelectedItem();
        if (tablerow != null){
            String strName = tablerow.getName();
            String strSurname = tablerow.getSurname();
            String strId = tablerow.getId();

            studentTextField.setText(Tool.stringForStudent(strName,strSurname,strId));
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
        this.olistNonattendance = fillAbsences(olistNonattendance);
        tableNonattendance.setItems(olistNonattendance);

    }

    /**
     * Fills the table of absences with those of the stored Person.
     * @author Alex JOBARD
     */
    ObservableList<TableNonattendance> fillAbsences(ObservableList<TableNonattendance> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Administration) Stockage.getPerson().getRole()).viewAbsences();
        for(int i = 0; i<array.size(); i++){
            obl.add(new TableNonattendance(
                array.get(i).get(0),
                array.get(i).get(1),
                array.get(i).get(2),
                array.get(i).get(3),
                array.get(i).get(4),
                array.get(i).get(5)));
        }
        return obl;
    }
}
