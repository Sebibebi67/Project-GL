package user;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import admin.Absence;
import tools.Query;
import tools.Stockage;
import tools.Tool;
import study.Module;

/**
 * 
 * This class contains all methods and attributes linked to an Exam
 * 
 * @author Sébastien HERT
 * @author Adam RIVIERE
 * 
 */
public abstract class Administration implements Role {

	private String login;
	private ArrayList<ArrayList<String>> students;
	private ArrayList<String> modules;


    /**
     * Constructor
     */
	public Administration () {}


    /**
     * @return login the login
     */
	public String getLogin(){
		return this.login;
	}

    /**
     * @param login the login to set
     */
	public void setLogin(String login){
		this.login = login;
	}

    /**
     * @return students the AL<AL<String> which contains the name, the firstname,the login and the course for each student
     */
	public ArrayList<ArrayList<String>> getStudents() {
		return this.students;
	}

    /**
     * @param students the AL<AL<String> which contains the name, the firstname, the login and the course for each student to set
     */
	public void setStudents(ArrayList<ArrayList<String>> students) {
		this.students = students;
	}

    /**
     * @return modules the AL<String> which contains the name of all modules
     */
	public ArrayList<String> getModules() {
		return this.modules;
	}

    /**
     * @param modules the AL<String> which contains the name of all modules to set
     */
	public void setModules(ArrayList<String> modules) {
		this.modules = modules;
	}
    /**
     * Inits all the arrays used is the class Administration
     * @author Sébastien HERT
     */
	public void initArray(){
		students = new ArrayList<>();
		modules = new ArrayList<>();
	}

    /**
     * Creates the list which contains the name, the firstname, the login and the course for each student to set
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @see Query.studentsSO()
     */
	public void createStudents(){
		ArrayList<ArrayList<String>> array = new ArrayList<>();
		array = Query.studentsSO();

		if (!array.get(0).isEmpty()){
			for (int i = 0; i < array.get(0).size(); i++){
				ArrayList<String> student = new ArrayList<>();
				for (int j = 0; j<4; j++){
					student.add(array.get(j).get(i));
				}
				this.students.add(student);
			}
		}
	}

    /**
     * Creates the list which contains the name of all modules
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @see Query.modulesSO()
     */
	public void createModules(){
		modules = Query.modulesSO();
	}

    /**
     * Creates the list which contains the name, the firstname and the login of a student, the module name, the date and the hour and if his/her  absence is jusitified. All parameters are Strings.
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @return list of absences for the UI
     * @see Query.allAbsences()
     */
	public ArrayList<ArrayList<String>> viewAbsences(){
		ArrayList<ArrayList<?>> array = new ArrayList<>();
		ArrayList<ArrayList<String>> absences = new ArrayList<>();

		array = Query.allAbsences();

		if(!array.get(0).isEmpty()){
			for (int i = 0; i < array.get(0).size();i++){
				ArrayList<String> absence = new ArrayList<>();
				for (int j = 0; j<4; j++){
					absence.add((String) array.get(j).get(i));
				}
				absence.add(Tool.dateToString(	(Date) array.get(5).get(i),
												(Time) array.get(6).get(i),
												(Time) array.get(7).get(i)));
				absence.add(Tool.booleanToString((Boolean) array.get(8).get(i)));
				absences.add(absence);
			}
		}
		return absences;
	}

    /**
     * Changes the justification of an absence
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @param login String
     * @param module String
     * @param date String
     * @see Query.justify(login, module, date, timeBegin, timeEnd)
     * @see Tool.stringToDate(date)
     */
	public void updateJustification(String login, String module, String date){
		Object[] obj = Tool.stringToDate(date);
		Query.justify(login, module, (Date) obj[0], (Time) obj[1], (Time) obj[2]);
	}
    /**
     * Creates the module by given the name
     * @author Sébastien HERT
     * @param name of the module in String
     * @return module a module (eventually null)
     */
	public Module getActiveModule(String name){
        for (int i = 0; i<this.modules.size(); i++){
            if( this.modules.get(i).equals(name)){
                return new Module(name);
            }
        }
        return null;
    }
    /**
     * Creates the student by given the login
     * @author Sébastien HERT
     * @param login of the student
     * @return student a student
     */
    public Student getActiveStudent(String login){
        Student student = new Student(login);
        return student;
	}
	
	/**
    * Returns the list of all the students for a given module for the UI
    * @author Adam RIVIERE
    * @return an Array with the list of all the students of the module with their average mark.
    */
    public ArrayList<ArrayList<String>> viewTableAttendees(){
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        String name = Stockage.getActiveModule().getName();
        ArrayList<ArrayList<?>> attendees = Query.moduleStudentsAverage(name);
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
	
	/**
    * Returns the list of all the students for a given module for the UI
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
                array.add(student);
            }
        }
        return array;
	}
	
	/**
    * Returns the list of all the marks for a given student in a given module.
    * @author Adam RIVIERE
    * @return an Array with the list of all the marks for a given student in a given module.
    */
    public ArrayList<ArrayList<String>> viewMarksAttendee(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
		Student student = Stockage.getStudent();
		String module = Stockage.getActiveModule().getName();
        ArrayList<String> examNames = new ArrayList<String>();
        for(int i = 0;i < student.getForm().getExams().size();i++){
			if(student.getForm().getExams().get(i).getName().equals(module)){
				examNames.add(student.getForm().getExams().get(i).getName());
			}
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
	
	/**
    * Returns the list of all the satisfaction reviews for a given module.
    * @author Adam RIVIERE
    * @return an Array with the list of all the satisfaction reviews for a given module.
    */
    public ArrayList<ArrayList<String>> viewTableSatisfaction(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
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
    * Returns the list of all the absences for a given module.
    * @author Sébastien HERT
    * @return an Array with all the absences for a given module.
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

    public ArrayList<String> viewListModules(){
         return this.modules;
    }

}