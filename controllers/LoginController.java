package controllers;

import java.net.URL;
// import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.Main;
import tools.Stockage;
import user.Administration;
import user.Professor;
import user.Role;
import user.Student;

public class LoginController extends ControllerAbs {

    @FXML
    private ResourceBundle resources;


    @FXML
    private AnchorPane anchorLogin;

    @FXML
    private URL location;

    @FXML
    private MenuItem quit;

    @FXML
    private PasswordField passwordtextField;

    @FXML
    private TextField loginField;

    @FXML
    private Button connexion;

    Stage window;

    @FXML
    void actionQuit(ActionEvent event) {

        fromAnchorClose(anchorLogin);

    }


    @FXML
    /**
     * Opens the window corresponding to the role of the user who logged in.
     * @author Alex JOBARD
     * @throws Exception
     */
    void loginProgram(ActionEvent event) throws Exception {

        String login = loginField.getText();
        String pswd = passwordtextField.getText();
        Main.createPerson(pswd, login);

        Role role = Stockage.getPerson().getRole();

        if (role instanceof Student){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_etudiant_selection_note.fxml"));

            Scene sceneFromAnchor = anchorLogin.getScene();
            sceneFromAnchor.setRoot(pane);

        } else if (role instanceof Professor) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_liste_modules.fxml"));

            Scene sceneFromAnchor = anchorLogin.getScene();
            sceneFromAnchor.setRoot(pane);

        } else if (role instanceof Administration) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_administration.fxml"));

            Scene sceneFromAnchor = anchorLogin.getScene();
            sceneFromAnchor.setRoot(pane);

        }else{
            this.warningLogin();
            System.out.println("ERROR");
        }


    }
}



