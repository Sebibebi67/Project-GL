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
import user.Professor;
import tables.*;
import study.Module;

/**
 * 
 * This class contains all the methods which are linked with the module view for
 * Professors
 * 
 * @author Alex JOBART
 * @author Sébastien HERT
 * 
 */

public class ModulesProfController extends ControllerAbs {

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
     * ComboBox
     *
     */
    @FXML
    private ComboBox<String> selectionModuleCombo;

    /*
     *
     * Columns for table of Module from a professor
     *
     */
    @FXML
    private TableView<TableModuleList> tableModulesTeacher;
    @FXML
    private TableColumn<TableModuleList, String> coursesColumn;

    /*
     *
     * ObservableList
     *
     */
    ObservableList<TableModuleList> olist = FXCollections.observableArrayList();

    /**
     * Goes back to the previous view
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void backFunction(ActionEvent event) throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/login.fxml"));

        Scene sceneFromAnchor = anchorModuleList.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    /**
     * Triggers the comboBox
     * 
     * @param event
     * @throws Exception
     */
    @FXML
    void SelectionModule(ActionEvent event) throws Exception {
        String value = selectionModuleCombo.getValue();
        if (value != null) {
            Stockage.setActiveModule(new Module(value));

            AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_prof_selection_note.fxml"));

            Scene sceneFromAnchor = anchorModuleList.getScene();
            sceneFromAnchor.setRoot(pane);
        }
    }

    /**
     * Quits the application
     * @param event
     */
    @FXML
    void quitFuntion(ActionEvent event) {
        fromAnchorClose(anchorModuleList);
    }

    /**
     * Iniatizes the view
     */
    @FXML
    public void initialize() {
        coursesColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        this.olist = fillModuleList(olist);
        tableModulesTeacher.setItems(olist);
    }

    /**
     * 
     * @param obl
     * @return
     */
    public ObservableList<TableModuleList> fillModuleList(ObservableList<TableModuleList> obl) {
        selectionModuleCombo.getItems().clear();

        ArrayList<String> array = ((Professor) Stockage.getPerson().getRole()).viewListModules();
        for (int i = 0; i < array.size(); i++) {
            obl.add(new TableModuleList(array.get(i)));
            selectionModuleCombo.getItems().add(array.get(i));
        }
        selectionModuleCombo.setEditable(false);
        return obl;
    }

}
