package tools;

import user.Person;
import user.Student;
import study.Module;

public class Stockage{

    private static Person person;
    private static Module activModule;
    private static Student student;
    
    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person p) {
        person = p;
    }

    public static Student getStudent(){
        return student;
    }

    public static Module getActivModule() {
		return activModule;
	}

    public static void setActivModule(Module module) {
		activModule = module;
    }
    
    public static void setActivModule(String name) {
        activModule = student.getActivModule(name);

    }

}