package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
// import javafx.scene.layout.Pane;

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

        AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene sceneFromAnchor = anchorListeModule.getScene();
        sceneFromAnchor.setRoot(pane);


    }

        @FXML
        void SelectionModule(ActionEvent event) throws Exception {

            String value = selectionModuleCombo.getValue();
        System.out.println(value);
        if(value != null) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_prof_selection_note.fxml"));

            Scene sceneFromAnchor = anchorListeModule.getScene();
            sceneFromAnchor.setRoot(pane);
        }
        }


    @FXML
    void fonctionQuit(ActionEvent event) {

        fromAnchorClose(anchorListeModule);

    }

    private void setData() {

        selectionModuleCombo.getItems().clear();

        selectionModuleCombo.getItems().addAll(
                "Module1 ",
                "Module 2",
                "Module 3",
                "Module 4",
                "Module 5");
        selectionModuleCombo.setEditable(false);
    }

    @FXML
    public void initialize() {
        this.setData();
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        olist.add(new TableModuleList("module1"));
        olist.add(new TableModuleList("module2"));
        olist.add(new TableModuleList("module3"));
        olist.add(new TableModuleList("module4"));
        olist.add(new TableModuleList("module5"));
        tableModulesProf.setItems(olist);
    }


}
