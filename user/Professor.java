package user;

import java.util.ArrayList;

import tools.Query;
import study.Module;

public class Professor implements Role{

    private String login;
    private ArrayList<Module> modules;
    private ArrayList<ArrayList<Object>> students;

    public Professor(){}

    public Professor(String login){
        this.login = login;
        this.createModule();
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public void createModule(){
        modules = new ArrayList<>();

        ArrayList<String> array = new ArrayList<>();
        array = Query.coursesTaught(this.login);

        for (int i = 0 ; i < array.size() ; i++){
            modules.add(new Module(array.get(i)));
        }
    }

    public void createListStudent(String module){
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

    public void newAbsence(){
    }

    public ArrayList<String> viewListModules(){
        ArrayList<String> array = new ArrayList<String>();
        for(int i = 0;i < this.modules.size();i++){
            array.add(this.modules.get(i).getName());
        }
        return array;
    }

    public ArrayList<ArrayList<String>> viewTableAttendees(String moduleName){
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        ArrayList<ArrayList<?>> attendees = Query.moduleStudentsAverage(moduleName);
        if(!attendees.isEmpty()){
            int size = attendees.get(0).size();
            for(int i = 0;i < size;i++){
                ArrayList<String> student = new ArrayList<String>();
                for(int j = 0;j < attendees.size();j++){
                    student.add(attendees.get(j).get(i).toString());
                }
                array.add(student);
            }
        }
        return array;
    }
    
    public ArrayList<ArrayList<String>> viewListAttendees(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        if(!this.students.isEmpty()){
            for(int i = 0;i < students.size();i++){
                ArrayList<String> student = new ArrayList<String>();
                student.add(this.students.get(i).get(0).toString());
                student.add(this.students.get(i).get(1).toString());
                array.add(student);
            }
        }
        return array;
    }

    public ArrayList<ArrayList<String>> viewMarksAttendee(String moduleName, String login){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        Student student = new Student(login);
        ArrayList<String> examNames = new ArrayList<String>();
        for(int i = 0;i < student.getForm().getExams().size();i++){
            examNames.add(student.getForm().getExams().get(i).getName());
        }
        for(int i = 0;i < student.getForm().getExams().size();i++){
            ArrayList<String> exam = new ArrayList<String>();
            exam.add(examNames.get(i));
            if(student.getForm().getMarkExams().get(examNames.get(i)) == -1){
                exam.add("");
            }else{
                exam.add(student.getForm().getMarkExams().get(examNames.get(i)).toString());
            }
            array.add(exam);
        }
        return array;
    }

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

}