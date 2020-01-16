package tools;

import user.Person;
import user.Student;
import study.Module;

public class Stockage{

    private static Person person;
    private static Module activeModule;
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

    public static Module getActiveModule() {
		return activeModule;
	}

    public static void setActiveModule(Module module) {
		activeModule = module;
    }
    
    public static void setActivModule(String name) {
        activeModule = student.getActiveModule(name);

    }

}