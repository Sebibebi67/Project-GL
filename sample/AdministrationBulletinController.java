package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AdministrationBulletinController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorBulletin;

    @FXML
    private MenuItem retourMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private ComboBox<String> comboPromotion;

    @FXML
    private ComboBox<String> comboFiliere;

    @FXML
    private ComboBox<String> comboEtudiant;

    @FXML
    private TableView<?> tableMoyennesEleve;

    @FXML
    private TableView<?> tableAbsencesEleve;

    @FXML
    private Button genererBulletinButton;

    @FXML
    private Button detailButton;

    @FXML
    private TextField aideJuryField;

    String filiere = new String("");
    String eleve = new String("");
    String promotion = new String("");
    @FXML
    void fonctionQuit(ActionEvent event) {
    fromAnchorClose(anchorBulletin);
    }

    @FXML
    void fonctionRetour(ActionEvent event) throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("vue_administration.fxml"));

        Scene sceneFromAnchor = anchorBulletin.getScene();
        sceneFromAnchor.setRoot(pane);



    }

    @FXML
    void genererBulletinBouton(ActionEvent event) {

    }

    @FXML
    void recupererAideJury(ActionEvent event) {

    }

    @FXML
    void selectionEtudiant(ActionEvent event) {

    }

    @FXML
    void selectionFiliere(ActionEvent event) {
        filiere = comboFiliere.getValue();
        if(filiere!=null) {
            setEtudiant();
        }
    }

    private void setEtudiant() {
        if (filiere.equalsIgnoreCase("TS")) {
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 1");
        }
        else if(filiere.equalsIgnoreCase("IPS")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 2","Eleve 3");
        }
        else if(filiere.equalsIgnoreCase("INFO1")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 4");
        }
        else if(filiere.equalsIgnoreCase("SNUM1")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 5","Eleve 6");
        }
        else if(filiere.equalsIgnoreCase("PHOT1")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 7");
        }

        else if(filiere.equalsIgnoreCase("IMR1")){
            comboEtudiant.getItems().clear();
            comboEtudiant.getItems().addAll("Eleve 666");
        }

        else if(filiere.equalsIgnoreCase("INFO2")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 8");
        }
        else if(filiere.equalsIgnoreCase("SNUM2")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 9","Eleve 10");
        }
        else if(filiere.equalsIgnoreCase("PHOT2")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 11");
        }
        else if(filiere.equalsIgnoreCase("IMR2")){
            comboEtudiant.getItems().clear();
            comboEtudiant.getItems().addAll("Eleve 777");
        }

        else if(filiere.equalsIgnoreCase("INFO3")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 12");
        }
        else if(filiere.equalsIgnoreCase("SNUM3")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 12","Eleve 13");
        }
        else if(filiere.equalsIgnoreCase("PHOT3")){
            comboEtudiant.getItems().clear();

            comboEtudiant.getItems().addAll("Eleve 14");
        }
        else if(filiere.equalsIgnoreCase("IMR3")){
            comboEtudiant.getItems().clear();
            comboEtudiant.getItems().addAll("Eleve 888");
        }
        else{

        }

    }

    @FXML
    void selectionPromotion(ActionEvent event) {
        promotion = comboPromotion.getValue();
        if (promotion != null) {
            setComboFiliere();
        }

    }

    @FXML
    void selectionnerDetailButton(ActionEvent event) {

    }

    public void setComboPromotion(){
        comboPromotion.getItems().clear();

        comboPromotion.getItems().addAll(
                "2019-2022",
                "2020-2023",
                "2021-2024");
    }
    public void setComboFiliere() {
        if (promotion.equalsIgnoreCase("2019-2022")) {
            comboFiliere.getItems().clear();

            comboFiliere.getItems().addAll(
                    "TS",
                    "IPS",
                    "IMR1",
                    "INFO1",
                    "PHOTO1",
                    "SNUM1");
        }
        else if(promotion.equalsIgnoreCase("2020-2023")){
            comboFiliere.getItems().clear();

            comboFiliere.getItems().addAll(
                    "IMR2",
                    "INFO2",
                    "PHOTO2",
                    "SNUM2");
        }
        else if(promotion.equalsIgnoreCase("2021-2024")){
            comboFiliere.getItems().clear();

            comboFiliere.getItems().addAll(
                    "IMR3",
                    "INFO3",
                    "PHOTO3",
                    "SNUM3");
        }
        else{

        }
    }

    @FXML
    void initialize() {
        this.setComboPromotion();


    }
}
