package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import tables.TableModuleAbsence;
import tools.Query;
import tools.Stockage;
import tools.Tool;
import user.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tables.TableAverageGradeStudent;

public class AdministrationBulletinController extends ControllerAbs{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorReport;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private ComboBox<String> yearGroupCombo;

    @FXML
    private ComboBox<String> coursesCombo;

    @FXML
    private ComboBox<String> studentCombo;

    //columns for tableAverageGradeStudent

    @FXML
    private TableView<TableAverageGradeStudent> tableAverageGradeStudent;

    @FXML
    private TableColumn<TableAverageGradeStudent, String> moduleColumn;

    @FXML
    private TableColumn<TableAverageGradeStudent, String> averageGradeColumn;

    @FXML
    private TableColumn<TableAverageGradeStudent, String> retakeColumn;

    //columns for tableNonattendanceStudent

    @FXML
    private TableView<TableModuleAbsence> tableNonattendanceStudent;

    @FXML
    private TableColumn<TableModuleAbsence, String> NonattendanceDateColumn;

    @FXML
    private TableColumn<TableModuleAbsence, String> justificationColumn;

    @FXML
    private Button generateReportButton;

    @FXML
    private Button detailButton;

    @FXML
    private TextField helpToJuryField;

    ObservableList<TableModuleAbsence> olistAbsences = FXCollections.observableArrayList();

    ObservableList<TableAverageGradeStudent> olistMarks = FXCollections.observableArrayList();

    String courses = new String("");
    String student = new String("");
    String yearGroup = new String("");
    @FXML
    void quitFunction(ActionEvent event) {
    fromAnchorClose(anchorReport);
    }

    @FXML
    void backFunction(ActionEvent event) throws Exception {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_administration.fxml"));

        Scene sceneFromAnchor = anchorReport.getScene();
        sceneFromAnchor.setRoot(pane);
    }

    @FXML
    /**
     * Generates the short report of the selected student.
     * @author Dejan PARIS
     * @throws IOException
     */
    void generateReportFunction(ActionEvent event) throws IOException
    {
        String login = Tool.getLogin(studentCombo.getValue());
        ArrayList<Object> array = Query.userData(login);
        String firstname = (String) array.get(3);
        String surname = (String) array.get(2);
        Stockage.getStudent().getForm().generateReport(firstname, surname, Stockage.getStudent().getCourse(), 0);
        informCompletion();
    }

    @FXML
    /**
     * Gives the advice for the jury, based on the average marks of the selected student.
     * @author Dejan PARIS
     */
    void getHelpToJury()
    {
        int fx = 0;
        // int abs = 0;
        String text;
        ArrayList<Double> avg = new ArrayList<>();
        ObservableList<TableAverageGradeStudent> obl = tableAverageGradeStudent.getItems();
        for (int i = 0; i< obl.size(); i++)
        {
            avg.add(Double.parseDouble(obl.get(i).getAverageGrade()));
        }
        for (int i = 0; i< avg.size(); i++)
        {
            if (avg.get(i) < 11) fx++;
        }
        if (fx == 0) text = "Passage automatique";
        else if (fx < 2) text = "Redoublement";
        else text = "Exclusion";
        helpToJuryField.setText(text);
    }

    @FXML
    /**
     * Fills the tables with the selected student's information.
     * @author Alex JOBARD
     */
    void selectionStudent(ActionEvent event) {
        if(studentCombo.getValue() != null){
            String login = Tool.getLogin(studentCombo.getValue());
            Stockage.setStudent(new Student(login));
            this.olistAbsences = fillAbsences(this.olistAbsences);
            this.tableNonattendanceStudent.setItems(this.olistAbsences);
            this.olistMarks = fillMarks(this.olistMarks);
            this.tableAverageGradeStudent.setItems(this.olistMarks);
            getHelpToJury();
        }
    }

    /**
     * Alerts the user that a report has been created successfully.
     * @author Alex JOBARD
     */
    public void informCompletion() {
        Alert alertLogin = new Alert(Alert.AlertType.INFORMATION);
        alertLogin.setTitle("Confirmation");
        alertLogin.setContentText("Le bulletin a bien été généré");
        alertLogin.showAndWait();
    }

    /**
     * Fills obl with the absences of the stored student.
     * @param obl The observable list to fill
     * @return Completed observable list
     * @author Alex JOBARD
     */
    public ObservableList<TableModuleAbsence> fillAbsences (ObservableList<TableModuleAbsence> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = Stockage.getStudent().viewTableAbsences();
        for(int i = 0; i<array.size(); i++){
            obl.add(new TableModuleAbsence(array.get(i).get(0), array.get(i).get(1)));
        }
        return obl;
    }

    /**
     * Fills obl with the marks of the stored student.
     * @param obl The observable list to fill
     * @return Completed observable list
     * @author Alex JOBARD
     */
    public ObservableList<TableAverageGradeStudent> fillMarks(ObservableList<TableAverageGradeStudent> obl){
        obl.clear();
        ArrayList<ArrayList<String>> array = Stockage.getStudent().viewModuleAverage();
        for(int i = 0; i<array.size(); i++){
            obl.add(new TableAverageGradeStudent(array.get(i).get(0), array.get(i).get(1), array.get(i).get(2)));
        }
        return obl;
    }

    @FXML
    /**
     * Registers the selection of a course.
     * @author Alex JOBARD
     */
    void selectionCourses(ActionEvent event) {
        courses = coursesCombo.getValue();
        if(courses !=null) {
            setStudents();
        }
    }

    /**
     * Fills the dedicated combo box with the students linked to the selected course.
     * @author Alex JOBARD
     */
    private void setStudents() {
        studentCombo.getItems().clear();
        ArrayList<ArrayList<String>> array = Query.allStudentsInCourse(courses);
        ArrayList<ArrayList<String>> students = new ArrayList<ArrayList<String>>();
        if(!array.isEmpty()){
            for(int i = 0;i < array.get(0).size();i++){
                ArrayList<String> student = new ArrayList<String>();
                student.add(array.get(0).get(i).toString());
                student.add(array.get(1).get(i).toString());
                student.add(array.get(2).get(i).toString());
                students.add(student);
            }
            for (int i= 0; i< students.size(); i++){
                studentCombo.getItems().add(Tool.stringForStudent(students.get(i).get(0),
                                                                students.get(i).get(1),
                                                                students.get(i).get(2)));
            }
        }
    }

    @FXML
    /**
     * Registers the selection of a year.
     * @author Alex JOBARD
     */
    public void selectionYearGroup(ActionEvent event) {
        yearGroup = yearGroupCombo.getValue();
        if (yearGroup != null) {
            setComboCourses();
        }

    }

    @FXML
    /**
     * Generates the detailed report of the selected student.
     * @throws IOException
     * @author Dejan PARIS
     */
    public void detailedReportFunction(ActionEvent event) throws IOException
    {
        String login = Tool.getLogin(studentCombo.getValue());
        ArrayList<Object> array = Query.userData(login);
        String firstname = (String) array.get(3);
        String surname = (String) array.get(2);
        Stockage.getStudent().getForm().generateReport(firstname, surname, Stockage.getStudent().getCourse(), 1);
        informCompletion();
    }

    /**
     * Fills the dedicated combo box with the possible years.
     * @author Alex JOBARD
     */
    public void setComboYearGroup(){
        yearGroupCombo.getItems().clear();

        yearGroupCombo.getItems().addAll(
                "1ère Années",
                "2ème Années",
                "3ème Années");
    }

    /**
     * Fills the dedicated combo box with the possible courses.
     * @author Alex JOBARD
     */
    public void setComboCourses() {
        if (yearGroup.equalsIgnoreCase("1ère Années")) {
            coursesCombo.getItems().clear();

            coursesCombo.getItems().addAll(
                    "TS",
                    "IPS",
                    "IMR1",
                    "INFO1",
                    "PHOT1",
                    "SNUM1");
        }
        else if(yearGroup.equalsIgnoreCase("2ème Années")){
            coursesCombo.getItems().clear();

            coursesCombo.getItems().addAll(
                    "IMR2",
                    "INFO2",
                    "PHOT2",
                    "SNUM2");
        }
        else if(yearGroup.equalsIgnoreCase("3ème Années")){
            coursesCombo.getItems().clear();

            coursesCombo.getItems().addAll(
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
        this.setComboYearGroup();
        this.initColumnsFactory();
    }

    private void initColumnsFactory(){
        //nonattendance
        NonattendanceDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        justificationColumn.setCellValueFactory(new PropertyValueFactory<>("justification"));
        //grades
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("module"));
        averageGradeColumn.setCellValueFactory(new PropertyValueFactory<>("averageGrade"));
        retakeColumn.setCellValueFactory(new PropertyValueFactory<>("retake"));
    }
}
