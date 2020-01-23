package sample;

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
    void loginProgram(ActionEvent event) throws Exception {

        String c = loginField.getText();
        System.out.println(c);
        System.out.println(passwordtextField.getText());
        if (c.equalsIgnoreCase("a")) {
            //eleve
            AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_etudiant_selection_note.fxml"));

            Scene sceneFromAnchor = anchorLogin.getScene();
            sceneFromAnchor.setRoot(pane);

        } else if (c.equalsIgnoreCase("b")) {
            //prof
            AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_liste_modules.fxml"));

            Scene sceneFromAnchor = anchorLogin.getScene();
            sceneFromAnchor.setRoot(pane);

        } else if (c.equalsIgnoreCase("c")) {
            //administration
            AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_administration.fxml"));

            Scene sceneFromAnchor = anchorLogin.getScene();
            sceneFromAnchor.setRoot(pane);

        }

    }
}



