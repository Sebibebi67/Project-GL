package tools;

import user.*;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{

    private Stage window;
    public static void main(String[] args) {
        launch(args);
    }

    public static void createPerson(String pswd, String login){
        Person person = new Person(login, pswd);
        Stockage.setPerson(person);
    }

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
        window.setTitle("Test");
        window.setScene(new Scene(root));
        window.setResizable(true);
        window.show();

        // System.out.println("ok");

    }
}