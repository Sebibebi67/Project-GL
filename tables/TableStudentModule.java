package tables;

// import java.net.URL;
// import java.util.Optional;
// import java.util.ResourceBundle;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.AnchorPane;
// import javafx.stage.Stage;

public class TableStudentModule {

    private String name;
    private String firstName;
    private String mark;
    private String login;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMark() {
        return this.mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public TableStudentModule(String name, String firstName, String mark, String login){
        this.name = name;
        this.firstName = firstName;
        this.mark = mark;
        this.login = login;
    }

    }




