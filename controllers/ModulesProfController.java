package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
// import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.Pane;
import tools.Stockage;
import user.Professor;
import tables.*;

public class ModulesProfController extends ControllerAbs{


      ObservableList<TableModuleList> olist = FXCollections.observableArrayList();

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private AnchorPane anchorListeModule;

        @FXML
        private MenuItem retourMenu;

        @FXML
        private MenuItem quitterMenu;

        @FXML
        private TableView<TableModuleList> tableModulesProf;

        @FXML
        private TableColumn<TableModuleList, String> coursColumn;

        @FXML
        private ComboBox<String> selectionModuleCombo;

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception{

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/login.fxml"));

        Scene sceneFromAnchor = anchorListeModule.getScene();
        sceneFromAnchor.setRoot(pane);


    }

        @FXML
        void SelectionModule(ActionEvent event) throws Exception {

            String value = selectionModuleCombo.getValue();
            if(value != null) {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_prof_selection_note.fxml"));

                Scene sceneFromAnchor = anchorListeModule.getScene();
                sceneFromAnchor.setRoot(pane);
            }
        }


    @FXML
    void fonctionQuit(ActionEvent event) {

        fromAnchorClose(anchorListeModule);

    }

    @FXML
    public void initialize() {
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        this.olist = feelModuleList(olist);
        tableModulesProf.setItems(olist);
    }

    public ObservableList<TableModuleList> feelModuleList(ObservableList<TableModuleList> obl){
        selectionModuleCombo.getItems().clear();


        ArrayList<String> array = ((Professor) Stockage.getPerson().getRole()).viewListModules();
        for (int i = 0; i< array.size(); i++){
            obl.add(new TableModuleList(array.get(i)));
            selectionModuleCombo.getItems().add(array.get(i));
        }
        selectionModuleCombo.setEditable(false);
        return obl;
    }


}


