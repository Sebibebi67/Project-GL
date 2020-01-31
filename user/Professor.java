package user;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import admin.Absence;
import tools.Query;
import tools.SQL;
import tools.Stockage;
import tools.Tool;
import study.Module;

/**
 * 
 * This class contains all methods and attributes linked to a Professor
 * 
 * @author Sébastien HERT
 * @author Adam RIVIERE
 * @author Dejan PARIS
 * 
 */

public class Professor implements Role{

    private String login;
    private ArrayList<Module> modules;
    private ArrayList<ArrayList<Object>> students;

    /**
     * Constructor
     */
    public Professor(){}

    /**
     * Constructor
     * @param login login of the teacher
     */
    public Professor(String login){
        this.login = login;
        this.createModule();
    }

    /**
     * @return login of the professor
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
     * @return a list of modules
     */
    public ArrayList<Module> getModules() {
        return this.modules;
    }

    /**
     * @param modules list of modules to set
     */
    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    /**
     * Tests if a professor is equal to an other one
     * @author Dejan PARIS
     * @param professor teacher to test
     * @return true or false
     */
    public boolean equals(Professor professor)
    {
        return this.login.equals(professor.getLogin());
    }

    /**
     * Creates a list of modules from the database
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @see Query.coursesTaught
     */
    public void createModule(){
        modules = new ArrayList<>();

        ArrayList<String> array = new ArrayList<>();
        array = Query.coursesTaught(this.login);

        for (int i = 0 ; i < array.size() ; i++){
            modules.add(new Module(array.get(i)));
        }
    }

    /**
     * Creates a list of the students of a module from the database
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @param module name of the module
     * @see Query.attendees
     */
    public void createListStudent(String module){
        students = new ArrayList<>();
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        array = Query.attendees(module);
        if (!array.isEmpty()){
            for ( int i = 0; i < array.get(0).size(); i++){
                ArrayList<Object> student = new ArrayList<>();
                for (int j = 0; j < 3; j++){
                    student.add(array.get(j).get(i));
                }
                student.add(Query.studentAverage(module, (String) student.get(2)));
                students.add(student);
            }
        }
    }

    /**
    * Returns the list of all the modules for a given teacher to be displayed.
    * @author Sébastien HERT
    * @author Adam RIVIERE
    * @return an Array with the list of all the modules of the professor.
    */
    public ArrayList<String> viewListModules(){
        ArrayList<String> array = new ArrayList<String>();
        for(int i = 0;i < this.modules.size();i++){
            array.add(this.modules.get(i).getName());
        }
        return array;
    }

    /**
    * Returns the list of all the students for a given module to be displayed.
    * @author Sébastien HERT
    * @author Adam RIVIERE
    * @return an Array with the list of all the students of the module with their average mark.
    */
    public ArrayList<ArrayList<String>> viewTableAttendees(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        if(!this.students.isEmpty()){
            for(int i = 0;i < students.size();i++){
                ArrayList<String> student = new ArrayList<String>();
                student.add(this.students.get(i).get(0).toString());
                student.add(this.students.get(i).get(1).toString());
                student.add(Double.toString(Query.studentAverage(Stockage.getActiveModule().getName(), this.students.get(i).get(2).toString())));
                student.add(this.students.get(i).get(2).toString());
                array.add(student);
            }
        }
        return array;
    }
    
    /**
    * Returns the list of all the students for a given module to be displayed.
    * @author Sébastien HERT
    * @author Adam RIVIERE
    * @return an Array with the list of all the students of the module.
    */
    public ArrayList<ArrayList<String>> viewListAttendees(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        if(!this.students.isEmpty()){
            for(int i = 0;i < students.size();i++){
                ArrayList<String> student = new ArrayList<String>();
                student.add(this.students.get(i).get(0).toString());
                student.add(this.students.get(i).get(1).toString());
                student.add(this.students.get(i).get(2).toString());
                array.add(student);
            }
        }
        return array;
    }

    /**
    * Returns the list of all the marks for a given student in a given module to be displayed.
    * @author Sébastien HERT
    * @author Adam RIVIERE
    * @param moduleName name of the selected module
    * @param login login of the selected student
    * @return an Array with the list of all the marks for a given student in a given module.
    */
    public ArrayList<ArrayList<String>> viewMarksAttendee(String moduleName, String login){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        Student student = Stockage.getStudent();
        ArrayList<String> examNames = new ArrayList<String>();
        for(int i = 0;i < student.getForm().getExams().size();i++){
            if(student.getForm().getExams().get(i).getModule().getName().equals(moduleName)){
                examNames.add(student.getForm().getExams().get(i).getName());
            }
        }

        for(int i = 0;i < student.getForm().getExams().size();i++){
            ArrayList<String> exam = new ArrayList<String>();
            if (!examNames.isEmpty()){
                exam.add(examNames.get(i));
                if(student.getForm().getMarkExams().get(examNames.get(i)) == -1){
                    exam.add("");
                }else{
                    exam.add(student.getForm().getMarkExams().get(examNames.get(i)).toString());
                }
                array.add(exam);
            }
        }
        return array;
    }

    /**
    * Returns the list of all the satisfaction reviews for a given module to be displayed.
    * @author Sébastien HERT
    * @author Adam RIVIERE
    * @param moduleName name of the selected module
    * @return an Array with the list of all the satisfaction reviews for a given module.
    */
    public ArrayList<ArrayList<String>> viewTableSatisfaction(String moduleName){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        Module module = new Module(moduleName);
        for(int i = 0;i < module.getlistSatisfaction().size();i++){
            ArrayList<String> satisfaction = new ArrayList<String>();
            if(module.getlistSatisfaction().get(i).getRating() == -1){
                satisfaction.add("");
            }else{
                satisfaction.add(Integer.toString(module.getlistSatisfaction().get(i).getRating()));
            }
            satisfaction.add(module.getlistSatisfaction().get(i).getReview());
            array.add(satisfaction);
        }
        return array;
    }

    public ArrayList<ArrayList<String>> viewTableSatisfaction(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        // Module module = new Module(moduleName);
        Module module = Stockage.getActiveModule();
        for(int i = 0;i < module.getlistSatisfaction().size();i++){
            ArrayList<String> satisfaction = new ArrayList<String>();
            if(module.getlistSatisfaction().get(i).getRating() == -1){
                satisfaction.add("");
            }else{
                satisfaction.add(Integer.toString(module.getlistSatisfaction().get(i).getRating()));
            }
            satisfaction.add(module.getlistSatisfaction().get(i).getReview());
            array.add(satisfaction);
        }
        return array;
    }

    /**
     * Creates an AL<AL<String>> which contains all the names, dates and justifications of the absences of a student in a module to be displayed.
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @return the list of the absences
     */
    public ArrayList<ArrayList<String>> viewTableAbsences(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        Module module = Stockage.getActiveModule();
        Student student = Stockage.getStudent();
        ArrayList<Absence> absences = student.getForm().getAbsences();
        for(int i = 0;i < absences.size();i++){
            if(absences.get(i).getModuleName().equals(module.getName())){
                ArrayList<String> list = new ArrayList<String>();
                list.add(absences.get(i).getBeginDate().toString());
                list.add(Tool.booleanToString(absences.get(i).isJustified()));
                array.add(list);
            }
        }

        return array;
    }

    /**
     * Creates a new absence
     * @author Sébastien HERT
     * @param beginHourH begining hour of the absence
     * @param beginHourM begining minute of the absence
     * @param endHourH end hour of the absence
     * @param endHourM end minute of the absence
     * @param moduleName module concerned
     * @param loginS login of the student
     * @param date date of the absence
     */
    public void newAbsence(String beginHourH, String beginHourM, String endHourH, String endHourM, String moduleName, String loginS, Date date ){
        if(Tool.isTime(beginHourH, beginHourM) && Tool.isTime(endHourH, endHourM)){
            Time beginTime = Tool.newTime(beginHourH, beginHourM);
            Time endTime = Tool.newTime(endHourH, endHourM);
            SQL.createAbsence(beginTime, endTime, moduleName, loginS, login, date);
        }else{
            //error message
        }
    }

    /**
     * Gets the active module
     * @author Sébastien HERT
     * @param name name of the module
     * @return the active module
     */
    public Module getActiveModule(String name){
        for (int i = 0; i<this.modules.size(); i++){
            if( this.modules.get(i).getName().equals(name)){
                return this.modules.get(i);
            }
        }
        return null;
    }

    /**
     * Gets the active student
     * @author Sébastien HERT
     * @param login of the student
     * @return the active student
     */
    public Student getActiveStudent(String login){
        Student student = new Student(login);
        return student;
    }

    public ArrayList<ArrayList<String>> viewAbsences(){
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        ArrayList<ArrayList<?>> query = Query.absenceModule(
            Stockage.getLoginStudent(),
            Stockage.getActiveModule().getName());
        if (!query.isEmpty()){
            for (int i = 0; i< query.get(0).size(); i++){
                ArrayList<String> abs = new ArrayList<>();
                String date = Tool.dateToString(
                    (Date) query.get(0).get(i),
                    (Time) query.get(1).get(i),
                    (Time) query.get(3).get(i));
                abs.add(date);
                abs.add(Tool.booleanToString((Boolean) query.get(4).get(i)));
                array.add(abs);
            }
        }
    return array;
    }

    public ArrayList<ArrayList<String>> viewMarks(){
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        ArrayList<ArrayList<?>> query = Query.exams(
            Stockage.getActiveModule().getName(),
            Stockage.getLoginStudent());
        if(!query.isEmpty()){
            for( int i = 0; i<query.get(0).size(); i++){
                ArrayList<String> exam = new ArrayList<>();
                exam.add( (String) (query.get(0).get(i)) );
                exam.add((query.get(1).get(i)).toString());
                array.add(exam);
            }
        }
        return array;
    }

}