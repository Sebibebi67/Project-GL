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

    public static Professor getProfessor() {
        return professor;
    }

    public static void setProfessor(Professor p) {
        professor = p;
    }
    
    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person p) {
        person = p;
    }

    public static void setStudent(Student s){
        student = s;
    }

    public static Student getStudent(){
        return student;
    }

    public static Module getActiveModule() {
		return activeModule;
	}

    public static void setActiveModule(Module module) {
		activeModule = module;
    }
    
    public static void setActiveModuleStudent(String name) {
        activeModule = student.getActiveModule(name);
    }

    public static void setActiveModuleProfessor(String name) {
        activeModule = professor.getActiveModule(name);
    }

}