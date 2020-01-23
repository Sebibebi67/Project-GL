package sample;
import java.net.URL;
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

        combobox.getItems().addAll(
                "Module1 ",
                "Module 2",
                "Module 3",
                "Module 4",
                "Module 5");

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

        AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));

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
        olist.add(new TableModel("ue1","module1","moyenne1"));
        olist.add(new TableModel("ue2","module2","moyenne2"));
        tableNotesEleve.setItems(olist);
    }

}
