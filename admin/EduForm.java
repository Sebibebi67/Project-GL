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
    private Map<String, Double> markModules;
    private ArrayList<Exam> exams;
    private Map<String, Double> markExams;

    public EduForm() {
    }

    public EduForm(String login) {
        this.login = login;
        this.createAbsences();

        this.createModuleList();
        this.createMarkModules(); 

        this.createExams();
        
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<Absence> getAbsences(){
        return this.absences;
    }

    public setAbsences(ArrayList<Absence> absences){
        this.absences = absences;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<Exam> getExams() {
		return this.exams;
	}

	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
    }
    
    public Map<String, Double> getMarkExams(){
        return this.markExams;
    }

    public void setMarkExams(Map<String, Double>  map){
        this.markExams = map;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
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

    public void createMarkModules(){
        markModules = new HashMap<>();
        for (int i =0; i<modules.size(); i++){
            String moduleName = modules.get(i).getName();
            double average = Query.studentAverage(moduleName, this.login);
            markModules.put(moduleName, average);
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
        markExams = new HashMap<>();
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
                    exams.add(exam);
                    markExams.put(exam.getName(), (Double) array.get(1).get(j));
                }
            }
        }
    }

}