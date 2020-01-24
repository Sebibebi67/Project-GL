package controllers;

import tools.Stockage;
import user.Student;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private AnchorPane anchorEtuNote;

        @FXML
        private VBox vbox_etudiant;

        @FXML
        private MenuItem retourMenu;

        @FXML
        private MenuItem quitMenu;

        @FXML
        private ComboBox<String> comboModuleNotes;

        @FXML
        private TableView<?> tableNotesModule;

        @FXML
        private ComboBox<String> comboModuleAbsence;

        @FXML
        private TableView<?> tableAbsencesModule;

        @FXML
        private ComboBox<String> comboModuleStaisfaction;

        @FXML
        private ComboBox<String> noteSatisfactionCombo;

        @FXML
        private TextField commentaireSatisfactionTextField;

        @FXML
        private Button ajoutSatisfactionButton;

        @FXML
        private TableView<TableModel> tableNotesEleve;

        @FXML
        private TableColumn<TableModel, String> uETableColumn;

        @FXML
        private TableColumn<TableModel, String> moduleTableColumn;

        @FXML
        private TableColumn<TableModel, String> moyenneTableColumn;

        @FXML
        void ajouterSatisfaction(ActionEvent event) {

            System.out.println(commentaireSatisfactionTextField.getText());

        }


        @FXML
        void selectionNoteSatisfaction(ActionEvent event) {
            String value = noteSatisfactionCombo.getValue();
            if(value != null) {
                System.out.println(value);
            }

        }


    public void setData(ComboBox<String> combobox){

        combobox.getItems().clear();

        combobox = this.feelComboBoxModule(combobox);

    }

    public ComboBox<String> feelComboBoxModule(ComboBox<String> combobox){
        ArrayList<String> array =  ( (Student) Stockage.getPerson().getRole() ).viewlistModules();
        for (int i = 0; i<array.size(); i++){
            combobox.getItems().add(array.get(i));
        }
        return combobox;
    }




    @FXML
    void selectionModuleAbsence(ActionEvent event) {
        String value = comboModuleAbsence.getValue();
        if(value != null) {
            System.out.println(value);
        }

    }

    @FXML
    void selectionModuleNote(ActionEvent event) {
        String value = comboModuleNotes.getValue();
        if(value != null) {
            System.out.println(value);
        }


    }


    @FXML
    void selectionModuleSatisfaction(ActionEvent event) {
        String value = comboModuleStaisfaction.getValue();
        if(value != null) {
            System.out.println(value);
        }

    }

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/login.fxml"));

        Scene sceneFromAnchor = anchorEtuNote.getScene();
        sceneFromAnchor.setRoot(pane);


    }


    @FXML
    void fonctionQuit(ActionEvent event) {

        fromAnchorClose(anchorEtuNote);

    }

    ObservableList<TableModel> olist = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        this.setData(comboModuleAbsence);
        this.setData(comboModuleNotes);
        this.setData(comboModuleStaisfaction);
        noteSatisfactionCombo.getItems().clear();

        noteSatisfactionCombo.getItems().addAll(
                "1 ",
                "2",
                "3",
                "4",
                "5");
        uETableColumn.setCellValueFactory(new PropertyValueFactory<>("ue"));
        moduleTableColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        moyenneTableColumn.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
        this.olist = this.feelTableMark(this.olist);
        tableNotesEleve.setItems(this.olist);
    }

    public ObservableList<TableModel> feelTableMark( ObservableList<TableModel> obl){
        ArrayList<ArrayList<String>> array = ( (Student) Stockage.getPerson().getRole() ).viewTableMark();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableModel( array.get(i).get(0),
                                    array.get(i).get(1),
                                    array.get(i).get(2)));
        }
        return obl;
    }

}
