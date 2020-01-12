package admin;

import user.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import study.*;
import study.Module;
import tools.Query;

/**
 * 
 * This class contains all the methods and attributs which are used in the Form
 * of a student.
 * 
 * @author Sébastien HERT
 * 
 */

public class EduForm {

    private Student student;

    private String login;

    private ArrayList<Absence> absences;
    private ArrayList<Module> modules;
    private Map<Exam, Integer> exams;

    public EduForm() {
    }

    public EduForm(String login) {
        this.login = login;
        this.createAbsences();
        this.createModuleList();
        this.createExams();
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Map<Exam, Integer> getExams(){
        return exams;
    }

    public void setExam(Map<Exam, Integer> exams){
        this.exams = exams;
    }

    public void createAbsences() {
        ArrayList<ArrayList<?>> array = new ArrayList<>();
        array = Query.absence(this.login);

        if (!array.isEmpty()) {
            for (int i = 0; i < array.get(0).size(); i++) {
                absences.add(new Absence((Date) array.get(0).get(i), (Time) array.get(1).get(i),
                        (Date) array.get(2).get(i), (Time) array.get(3).get(i), (Boolean) array.get(4).get(i)));
            }
        }

    }

    public void createModuleList(){
        ArrayList<String> modulesNames = new ArrayList<>();

        modulesNames = Query.courses(this.login);

        for (int i = 0 ; i < modulesNames.size(); i++){
            modules.add(new Module(modulesNames.get(i).toString()));
        }
    }

    public void createExams(){
        exams = new HashMap<>();


        for( int i = 0; i< modules.size(); i++ ){
            ArrayList<ArrayList<?>> array = new ArrayList<>();
            array = Query.exams(this.modules.get(i).getName(), this.login);

            if (! array.isEmpty()){
                for ( int j = 0; j< array.get(0).size(); j++ ){
                    Exam exam = new Exam(
                        (String) array.get(0).get(j),
                        (Date) array.get(3).get(j),
                        (Integer) array.get(2).get(j),
                        modules.get(i)
                    );
                    exams.put(exam, (Integer) array.get(1).get(j));
                }
            }
        }
    }

}