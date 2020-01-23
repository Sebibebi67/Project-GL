package sample;

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

public class TableModel {

    String ue;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    String module;

    public String getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(String moyenne) {
        this.moyenne = moyenne;
    }

    String moyenne;

    public String getUe() {
        return ue;
    }

    public void setUe(String ue) {
        this.ue = ue;
    }

    public TableModel(String ue, String module, String moyenne){
        this.ue = ue;
        this.module = module;
        this.moyenne = moyenne;
    }

    }




