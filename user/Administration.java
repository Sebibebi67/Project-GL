package user;

import java.util.ArrayList;

import tools.Query;

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
			}
		}
	}

	public void createModules(){
		modules = Query.modulesSO();
	}

}