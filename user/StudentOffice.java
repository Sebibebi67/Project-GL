package user;

import java.util.ArrayList;

import tools.Query;

public class StudentOffice extends Administration{

	ArrayList<ArrayList<String>> students;
	ArrayList<String> modules;


	public StudentOffice () {
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

	public void newAbsence(){}

}
