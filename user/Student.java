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

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
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
    }

    public ArrayList<ArrayList<?>> viewTableMark(){
        ArrayList<ArrayList<?>> list = new ArrayList<>();
        ArrayList<Module> modules = this.form.getModules();

        for (int i =0; i<modules.size(); i++){
            ArrayList<String> module = new ArrayList<>();

            String unitName = modules.get(i).getUnit().getName();
            module.add(unitName);

            String moduleName = modules.get(i).getName();
            module.add(moduleName);

            Double mark = Query.studentAverage(moduleName, this.login);
            if (mark == -1){
                module.add("");
            }else{
                module.add(mark.toString());
            }
            list.add(module);
        }
        return list;
    }

    public Module getActiveModule(String name){
        ArrayList<Module> modules = this.form.getModules();
        for (int i = 0; i<modules.size(); i++){
            if( modules.get(i).getName().equals(name)){
                return modules.get(i);
            }
        }
        return null;
    }


}