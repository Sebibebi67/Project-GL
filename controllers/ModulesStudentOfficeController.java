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
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import tools.Stockage;
import user.Administration;
import tables.*;
import study.Module;

/**
 * 
 * This class contains all the methods which are linked with the Module view for
 * Administration
 * 
 * @author Alex JOBARD
 * @author Adam RIVIERE
 * 
 */

public class ModulesStudentOfficeController extends ControllerAbs {

    
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private AnchorPane anchorModuleList;

    @FXML
    private MenuItem backMenu;
    @FXML
    private MenuItem quitMenu;

    /*
    *
    * Column for table of module from all students
    *
    */
    @FXML
    private TableView<TableModuleList> tableModulesTeacher;
    @FXML
    private TableColumn<TableModuleList, String> coursesColumn;

    /*
    *
    * ComboBox
    *
    */
    @FXML
    private ComboBox<String> selectionModuleCombo;

    /*
    *
    * ObservableList
    *
    */
    ObservableList<TableModuleList> olist = FXCollections.observableArrayList();

    /**
     * Goes back to the previous view
     * @param event
     * @throws Exception
     * @author Alex JOBARD
     */
    @FXML
    void backFunction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_administration.fxml"));

        Scene sceneFromAnchor = anchorModuleList.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    /**
     * Triggers the selectionModuleCombo comboBox
     * @param event
     * @author Alex JOBARD
     */
    @FXML
    void SelectionModule(ActionEvent event) throws Exception {
        String value = selectionModuleCombo.getValue();
        if (value != null) {
            Stockage.setActiveModule(new Module(value));

            AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_admin_modules.fxml"));

            Scene sceneFromAnchor = anchorModuleList.getScene();
            sceneFromAnchor.setRoot(pane);
        }
    }

    /**
     * Quits the app
     * @param event
     * @author Alex JOBARD
     */
    @FXML
    void quitFuntion(ActionEvent event) {
        fromAnchorClose(anchorModuleList);
    }

    /**
     * Initializes the view
     * @author Alex JOBARD
     * @author Adam RIVIERE
     */
    @FXML
    public void initialize() {
        coursesColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        this.olist = fillModuleList(olist);
        tableModulesTeacher.setItems(olist);
    }

    /**
     * Fills the modules observableList and returns it
     * @param obl observableList of TableModuleList
     * @return obl observableList of TableModuleList
     * @author Adam RIVIERE
     */
    public ObservableList<TableModuleList> fillModuleList(ObservableList<TableModuleList> obl) {
        selectionModuleCombo.getItems().clear();
        ArrayList<String> array = ((Administration) Stockage.getPerson().getRole()).viewListModules();
        for (int i = 0; i < array.size(); i++) {
            obl.add(new TableModuleList(array.get(i)));
            selectionModuleCombo.getItems().add(array.get(i));
        }
        selectionModuleCombo.setEditable(false);
        return obl;
    }

}
