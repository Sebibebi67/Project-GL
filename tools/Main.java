package tools;

import user.*;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * This class contains all methods and attributes linked to an Exam
 * 
 * @author Sébastien HERT
 * @author Alex JOBARD
 * 
 */

public class Main extends Application{

    private Stage window;
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Instantiates a new Person.
     * @param pswd Their password
     * @param login Their login
     * @author Sébastien HERT
     */
    public static void createPerson(String pswd, String login){
        Person person = new Person(login, pswd);
        Stockage.setPerson(person);
    }

    /**
     * Starts the application
     * @param primarystage contains all the scenes
     * @author Alex JOBARD
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../scenes/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error");
        }
        window.setTitle("Univ Grap 1");
        window.setScene(new Scene(root));
        window.setResizable(true);
        window.show();

    }
}