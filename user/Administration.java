package user;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import tools.Query;
import tools.Tool;

public abstract class Administration implements Role {

	private String login;
	ArrayList<ArrayList<String>> students;
	ArrayList<String> modules;

	public Administration () {

	}

	public String getLogin()
	{
		return this.login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public ArrayList<ArrayList<String>> getStudents() {
		return this.students;
	}

	public void setStudents(ArrayList<ArrayList<String>> students) {
		this.students = students;
	}

	public ArrayList<String> getModules() {
		return this.modules;
	}

	public void setModules(ArrayList<String> modules) {
		this.modules = modules;
	}

	public void initArray(){
		students = new ArrayList<>();
		modules = new ArrayList<>();
	}

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

	public void createModules(){
		modules = Query.modulesSO();
	}

	
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

}