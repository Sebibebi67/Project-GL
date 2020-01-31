package controllers;

import java.net.URL;
import java.util.ArrayList;
// import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import tables.*;
import tools.SQL;
import tools.Stockage;
import tools.Tool;
import user.Professor;

// import javax.swing.*;


public class AjoutNoteController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorAddGrade;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private TextField nameGradeField;

    @FXML
    private TextField coefficientField;

    @FXML
    private TableView<TableNewGrade> tableNewGrades;

    @FXML
    private TableColumn<TableNewGrade, String> nameColumn;

    @FXML
    private TableColumn<TableNewGrade, String> surnameColumn;

    @FXML
    private TableColumn<TableNewGrade, String> gradeColumn;

    @FXML
    private TableColumn<TableNewGrade, String> idColumn;


    ObservableList<TableNewGrade> olistNewGrade= FXCollections.observableArrayList();

    @FXML
    private MenuItem validationGradeItem;

    /**
     * Displays an error window
     * @author Alex JOBARD
     */
    private void alertFill(){
        Alert alertLogin = new Alert(Alert.AlertType.WARNING);
        alertLogin.setTitle("Incomplet");
        alertLogin.setContentText("Cette évaluation a déjà été entrée.");
        alertLogin.showAndWait();
    }

    @FXML
    /**
     * Verifies that the entered data is in the correct format before registering the new grade in the database.
     * @author Alex JOBARD
     */
    void validationAllGrade(ActionEvent event){
        try {
            String module = Stockage.getActiveModule().getName();
            ObservableList<TableNewGrade> allGrade;
            TableNewGrade singleGrade;
            allGrade = tableNewGrades.getItems();
            if (!allGrade.isEmpty()) {
                for(int i = 0;i < allGrade.size();i++){
                    singleGrade = allGrade.get(i);
                    if(Tool.isInt(singleGrade.getGrade()) && Tool.isInt(singleGrade.getCoefficient())){
                        String id = singleGrade.getId();
                        int grade = Tool.stringToInt(singleGrade.getGrade());
                        String testName = singleGrade.getTestName();
                        int coefficient = Tool.stringToInt(singleGrade.getCoefficient());
                        SQL.note(testName, grade, coefficient, id, module);
                    }
                }
            }
            callingBack();
        } catch (Exception e) {
            alertFill();
        }
        
    }

    /**
     * @see backFunction
     */
    void callingBack() throws Exception{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_prof_selection_note.fxml"));

        Scene sceneFromAnchor = anchorAddGrade.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    @FXML
    /**
     * Quits the app
     */
    void quitFunction(ActionEvent event) {

        fromAnchorClose(anchorAddGrade);
    }

    @FXML
    /**
     * Returns to the previous window
     */
    void backFunction(ActionEvent event) throws Exception{
        callingBack();
    }

    @FXML
    /**
     * Sets the name of all the grades in the table to the entered name.
     * @author Alex JOBARD
     */
    void setAllNameGrade(ActionEvent event){
        ObservableList<TableNewGrade> allGrade;
        allGrade = tableNewGrades.getItems();
        allGrade.forEach((obl) -> {
            obl.setTestName(nameGradeField.getText());
        });
    }

    @FXML
    /**
     * Sets the coefficient of all the grades int he table to the entered coefficient.
     * @author Alex JOBARD
     */
    void setAllCoeff(ActionEvent event){
        ObservableList<TableNewGrade> allGrade;
        allGrade = tableNewGrades.getItems();
        allGrade.forEach((obl) -> {
            obl.setCoefficient(coefficientField.getText());
        });
    }

    /**
     * Fills the student observableList and returns it
     * @param obl observableList of TableStudentModule
     * @return obl observableList of TableStudentModule
     * @author Sébastien HERT
     */
    public ObservableList<TableNewGrade> fillStudents(ObservableList<TableNewGrade> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = ((Professor) Stockage.getPerson().getRole()).viewTableAttendees();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableNewGrade( array.get(i).get(0),
                    array.get(i).get(1),
                    "à remplir",
                    "PlaceHolder",
                    "PlaceHolder",
                    array.get(i).get(3)));
        }
        return obl;
    }




    @FXML
    /**
     * Registers the newly entered grade.
     * @author Alex JOBARD
     */
    void onEditGrade(TableColumn.CellEditEvent<TableNewGrade, String> event) {
        TableNewGrade modifiedNewGrade = tableNewGrades.getSelectionModel().getSelectedItem();
        modifiedNewGrade.setGrade(event.getNewValue());
    }

    /**
     * Gives names to all columns.
     * @author Alex JOBARD
     */
    private void initColumns(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    /**
     * Fills all entries in the table with the corresponding data.
     * @author Alex JOBARD
     */
    void initialize() {
        initColumns();
        fillStudents(olistNewGrade);
        tableNewGrades.setItems(olistNewGrade);
        tableNewGrades.setEditable(true);
        gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    }
}





