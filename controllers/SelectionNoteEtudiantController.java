package controllers;

import tools.Stockage;
import user.Student;
import tables.*;
// import study.Module;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

// import javax.swing.table.TableModel;

// import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SelectionNoteEtudiantController extends ControllerAbs{



    ObservableList<TableGradesStudent> olistGradeStudent = FXCollections.observableArrayList();
    ObservableList<TableGradesModule> olistGradeModule = FXCollections.observableArrayList();
    ObservableList<TableModuleAbsence> olistNonattendanceModule = FXCollections.observableArrayList();

    private String mark = null;

        @FXML
        private TableColumn<TableGradesModule, String> nameGradeColumn;

        @FXML
        private TableColumn<TableGradesModule , String> gradeColumn;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private AnchorPane anchorGradeStudent;

        @FXML
        private VBox vbox_student;

        @FXML
        private MenuItem backMenu;

        @FXML
        private MenuItem quitMenu;

        @FXML
        private ComboBox<String> comboModuleGrades;

        @FXML
        private TableView<TableGradesModule> tableGradesModule;

        @FXML
        private ComboBox<String> comboModuleNonattendance;

        @FXML
        private TableView<TableModuleAbsence> tableNonattendanceModule;

        @FXML
        private TableColumn<TableModuleAbsence, String> dateColumn;

        @FXML
        private TableColumn<TableModuleAbsence, String> justificationColumn;

        @FXML
        private ComboBox<String> comboModuleSatisfaction;

        @FXML
        private ComboBox<String> gradeSatisfactionCombo;
        
        @FXML
        private TextField commentarySatisfactionTextField;
        
        @FXML
        private Button addSatisfactionButton;
        
        @FXML
        private TableView<TableGradesStudent> tableGradeStudent;
        
        @FXML
        private TableColumn<TableGradesStudent, String> uETableColumn;
        
        @FXML
        private TableColumn<TableGradesStudent, String> moduleTableColumn;
        
        @FXML
        private TableColumn<TableGradesStudent, String> averageGradeTableColumn;
        

        private void warningAlreadySent(){
            Alert alertLogin = new Alert(Alert.AlertType.WARNING);
            alertLogin.setTitle("Déjà envoyée");
            alertLogin.setContentText("vous avez déjà envoyé une satisfaction");
            alertLogin.showAndWait();
        }

        private void warningCompletion(){
            Alert alertLogin = new Alert(Alert.AlertType.INFORMATION);
            alertLogin.setTitle("Confirmation");
            alertLogin.setContentText("La satisfaction a bien été envoyée");
            alertLogin.showAndWait();
        }

        private void warningIncomplete(){
            Alert alertLogin = new Alert(Alert.AlertType.WARNING);
            alertLogin.setTitle("Incomplet");
            alertLogin.setContentText("Satisfaction non envoyée : merci de remplir tous les champs");
            alertLogin.showAndWait();
        }


        @FXML
        void addSatisfaction(ActionEvent event) {
            
            String review = commentarySatisfactionTextField.getText();

            if (!(mark==null || comboModuleSatisfaction.getValue() == null)){
                ((Student) Stockage.getPerson().getRole()).newSatisfaction(mark, review);
                mark = null;
                this.commentarySatisfactionTextField.setText("Commentaire");
            }else{
                this.commentarySatisfactionTextField.setText("Satisfaction non envoyée : merci de remplir tous les champs");
            }
        }


        @FXML
        void selectionGradeSatisfaction(ActionEvent event) {
            mark = gradeSatisfactionCombo.getValue();
        }


    public void setData(ComboBox<String> combobox){

        combobox.getItems().clear();

        combobox = this.fillComboBoxModule(combobox);

    }

    public ComboBox<String> fillComboBoxModule(ComboBox<String> combobox){
        ArrayList<String> array =  ( (Student) Stockage.getPerson().getRole() ).viewlistModules();
        for (int i = 0; i<array.size(); i++){
            combobox.getItems().add(array.get(i));
        }
        return combobox;
    }




    @FXML
    void selectionModuleNonattendance(ActionEvent event) {
        String module = comboModuleNonattendance.getValue();
        if (module!=null){
            Stockage.setActiveModuleStudent(module);
            comboModuleGrades.setValue(module);

            this.olistNonattendanceModule = fillTableAbsencesModule(this.olistNonattendanceModule);
            tableNonattendanceModule.setItems(this.olistNonattendanceModule);
        }

    }

    @FXML
    void selectionModuleGrade(ActionEvent event) {
        String module = comboModuleGrades.getValue();
        if (module!=null){
            Stockage.setActiveModuleStudent(module);
            comboModuleNonattendance.setValue(module);

            this.olistGradeModule = fillTableMarkModule(this.olistGradeModule);
            tableGradesModule.setItems(this.olistGradeModule);
        }


    }


    @FXML
    void selectionModuleSatisfaction(ActionEvent event) {
        Stockage.setActiveModuleStudent(comboModuleSatisfaction.getValue());
    }

    @FXML
    void backFunction(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/login.fxml"));

        Scene sceneFromAnchor = anchorGradeStudent.getScene();
        sceneFromAnchor.setRoot(pane);


    }


    @FXML
    void quitFunction(ActionEvent event) {

        fromAnchorClose(anchorGradeStudent);

    }


    private void initColumns(){
        uETableColumn.setCellValueFactory(new PropertyValueFactory<>("ue"));
        moduleTableColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        averageGradeTableColumn.setCellValueFactory(new PropertyValueFactory<>("moyenne"));

        nameGradeColumn.setCellValueFactory(new PropertyValueFactory<>("nameGrade"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        justificationColumn.setCellValueFactory(new PropertyValueFactory<>("justification"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));


    }

    @FXML
    public void initialize() {
        initColumns();
        this.setData(comboModuleNonattendance);
        this.setData(comboModuleGrades);
        this.setData(comboModuleSatisfaction);

        gradeSatisfactionCombo.getItems().clear();
        gradeSatisfactionCombo.getItems().addAll("1 ", "2", "3", "4", "5");


        this.olistGradeStudent = this.fillTableMark(this.olistGradeStudent);
        tableGradeStudent.setItems(this.olistGradeStudent);
    }

    public ObservableList<TableGradesStudent> fillTableMark(ObservableList<TableGradesStudent> obl){
        ArrayList<ArrayList<String>> array = ( (Student) Stockage.getPerson().getRole() ).viewTableMark();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableGradesStudent( array.get(i).get(0),
                                    array.get(i).get(1),
                                    array.get(i).get(2)));
        }
        return obl;
    }

    public ObservableList<TableGradesModule> fillTableMarkModule(ObservableList<TableGradesModule> obl){
        ArrayList<ArrayList<String>> array = Stockage.getStudent().viewTableModuleMarks();
        obl.clear();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableGradesModule(  array.get(i).get(0),
                                            array.get(i).get(1)));
        }
        return obl;
    }

    public ObservableList<TableModuleAbsence> fillTableAbsencesModule(ObservableList<TableModuleAbsence> obl){
        ArrayList<ArrayList<String>> array = ( (Student) Stockage.getPerson().getRole() ).viewTableModuleAbsences();
        obl.clear();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableModuleAbsence(  array.get(i).get(0),
                                            array.get(i).get(1)));
        }
        return obl;
    }

}
