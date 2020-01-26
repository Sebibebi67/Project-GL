package user;

import java.util.ArrayList;

import admin.*;
import study.*;
import study.Module;
import tools.*;

/**
 * 
 * This class contains all methods and attributes linked to a Student
 * 
 * @author Sébastien HERT
 * @author Adam RIVIERE
 * @author Dejan PARIS
 * 
 */

public class Student implements Role{

    private String login;
    private EduForm form;
    private Group td;
    private Group tp;
    private Course course;

    private String juryAdvice;

    /**
     * Constructor
     */
    public Student(){
        
    }

    /**
     * Constructor
     * @param login of the student
     */
    public Student(String login){
        this.login = login;
        this.getData(login);

    }

    /**
     * @return the form of the student
     */
    public EduForm getForm() {
        return this.form;
    }

    /**
     * @param form form to set
     */
    public void setForm(EduForm form) {
        this.form = form;
    }

    /**
     * @return the TD group of the student
     */
    public Group getTd() {
        return this.td;
    }

    /**
     * @param td TD group to set
     */
    public void setTd(Group td) {
        this.td = td;
    }

    /**
     * @return the TP group of the student
     */
    public Group getTp() {
        return this.tp;
    }

    /**
     * @param tp TP group to set
     */
    public void setTp(Group tp) {
        this.tp = tp;
    }

    /**
     * @return the course of the student
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * @param course course to set
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * @return the advice to the jury concerning the student
     */
    public String getJuryAdvice() {
        return this.juryAdvice;
    }

    /**
     * @param juryAdvice advice to the jury to set
     */
    public void setJuryAdvice(String juryAdvice) {
        this.juryAdvice = juryAdvice;
    }

    /**
     * @return the login of the student
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * @param login login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Tests if a student is equal to an other one
     * @author Dejan PARIS
     * @param student student to test
     * @return true or false
     */
    public boolean equals(Student student)
    {
        return this.login.equals(student.getLogin());
    }

    /**
     * Gets the data of the student from the database
     * @author Sébastien HERT
     * @param login login of the student
     */
    public void getData(String login){
        ArrayList<Object> array = new ArrayList<>();
        array = Query.studentData(login);
        //string aideJury, int TP, int TD, String Filière

        this.juryAdvice = (String) array.get(0);
        this.tp = new Group("TP", (Integer) array.get(1));
        this.td = new Group("TD", (Integer) array.get(2));
        this.course = new Course(array.get(3).toString());


        this.form = new EduForm(login);
    }

    /**
     * Creates an AL which contains all the names of the units and the modules and the average mark associated to be displayed.
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @return the list of the modules and marks
     * @see Query.studentAverage
     */
    public ArrayList<ArrayList<String>> viewTableMark(){
        ArrayList<ArrayList<String>> list = new ArrayList<>();
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

    /**
     * Creates an AL<String> which contains th elist of all the modules of the student to be displayed
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @return the list of the modules
     */
    public ArrayList<String> viewlistModules(){
        ArrayList<String> array = new ArrayList<String>();
        for(int i = 0;i < this.form.getModules().size();i++){
            array.add(this.form.getModules().get(i).getName());
        }
        return array;
    }

    /**
     * Gets the active module
     * @author Sébastien HERT
     * @param name name of the module
     * @return the active module
     */
    public Module getActiveModule(String name){
        ArrayList<Module> modules = this.form.getModules();
        for (int i = 0; i<modules.size(); i++){
            if( modules.get(i).getName().equals(name)){
                return modules.get(i);
            }
        }
        return null;
    }

    /**
     * Creates an AL<AL<String>> which contains all the names of the exams and the associated marks for the active module to be displayed.
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @return the list of the exams and marks
     */
    public ArrayList<ArrayList<String>> viewTableModuleMarks(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        Module module = Stockage.getActiveModule();
        if (!this.form.getExams().isEmpty()){
            for(int i = 0;i < this.form.getExams().size();i++){
                if(this.form.getExams().get(i).getModule().getName().equals(module.getName())){
                    ArrayList<String> list = new ArrayList<String>();
                    String name= this.form.getExams().get(i).getName();
                    list.add(name);
                    list.add(this.form.getMarkExams().get(name).toString());
                    array.add(list);
                }
            }
        }
        return array;
    }

    /**
     * Creates a new satisfaction form
     * @param mark mark from the student for the active module
     * @param review review from the student for the active module
     */
    public void newSatisfaction(String mark, String review){
        if(Tool.isInt(mark)){
            int sMark = Tool.stringToInt(mark);
            SQL.satisfaction(this.login, Stockage.getActiveModule().getName(), review, sMark);
        }else{
            //error message
        }
    }

    /**
     * Creates an AL<AL<String>> which contains the dates and justifications of all the absences of the student in the active module
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @return the list of absences
     */
    public ArrayList<ArrayList<String>> viewTableModuleAbsences(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        Module module = Stockage.getActiveModule();
        for(int i = 0;i < this.form.getAbsences().size();i++){
            if(this.form.getAbsences().get(i).getModuleName().equals(module.getName())){
                ArrayList<String> absence = new ArrayList<String>();
                absence.add(Tool.dateToString(this.form.getAbsences().get(i).getBeginDate(), this.form.getAbsences().get(i).getBeginHour(), this.form.getAbsences().get(i).getEndHour()));
                absence.add(Tool.booleanToString(this.form.getAbsences().get(i).isJustified()));
                array.add(absence);
            }
        }
        return array;
    }

}