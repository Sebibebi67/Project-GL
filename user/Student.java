package user;

import java.util.ArrayList;

import admin.*;
import study.*;
import study.Module;
import tools.*;

public class Student implements Role{

    private String login;

    private EduForm form;
    private Group td;
    private Group tp;
    private Course course;

    private String juryAdvice;

    private ArrayList<Module> modules;


    public Student(){
        
    }

    public Student(String login){
        this.login = login;
        this.getData(login);

    }

    public EduForm getForm() {
        return this.form;
    }

    public void setForm(EduForm form) {
        this.form = form;
    }

    public Group getTd() {
        return this.td;
    }

    public void setTd(Group td) {
        this.td = td;
    }

    public Group getTp() {
        return this.tp;
    }

    public void setTp(Group tp) {
        this.tp = tp;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getJuryAdvice() {
        return this.juryAdvice;
    }

    public void setJuryAdvice(String juryAdvice) {
        this.juryAdvice = juryAdvice;
    }

    public void getData(String login){
        ArrayList<Object> array = new ArrayList<>();
        array = Query.studentData(login);
        //string aideJury, int TP, int TD, String Filière

        this.juryAdvice = array.get(0).toString();
        this.tp = new Group("TP", (Integer) array.get(1));
        this.td = new Group("TD", (Integer) array.get(2));
        this.course = new Course(array.get(3).toString());


        this.form = new EduForm(login);

        this.createModuleList();
    }

    public void createModuleList(){
        ArrayList<String> modulesNames = new ArrayList<>();

        modulesNames = Query.courses(this.login);

        for (int i = 0 ; i < modulesNames.size(); i++){
            modules.add(new Module(modulesNames.get(i).toString()));
        }
    }


}