package tools;

import user.Person;
import user.Professor;
import user.Student;
import study.Module;

public class Stockage{

    private static Person person;
    private static Module activeModule;
    private static Student student;
    private static Professor professor;
    private static String loginStudent;

    public String getLoginStudent() {
		return loginStudent;
	}

    public void setLoginStudent(String login) {
		loginStudent = login;
	}

    /**
     * @return stored Professor
     */
    public static Professor getProfessor() {
        return professor;
    }

    /**
     * @param p Professor to store
     */
    public static void setProfessor(Professor p) {
        professor = p;
    }
    
    /**
     * @return stored Person
     */
    public static Person getPerson() {
        return person;
    }

    /**
     * @param p Person to store
     */
    public static void setPerson(Person p) {
        person = p;
    }

    /**
     * @param p Student to store
     */
    public static void setStudent(Student s){
        student = s;
    }

    /**
     * @return stored Student
     */
    public static Student getStudent(){
        return student;
    }

    /**
     * @return active module
     */
    public static Module getActiveModule() {
		return activeModule;
	}

    /**
     * @param module new active module
     */
    public static void setActiveModule(Module module) {
		activeModule = module;
    }
    
    /**
     * Sets the active module according to the student's one.
     * @param name Student whose active module will be stored
     */
    public static void setActiveModuleStudent(String name) {
        activeModule = student.getActiveModule(name);
    }
    
    /**
     * Sets the active module according to the professor's one.
     * @param name Professor whose active module will be stored
     */
    public static void setActiveModuleProfessor(String name) {
        activeModule = professor.getActiveModule(name);
    }

}