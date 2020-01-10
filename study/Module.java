package study;

import java.util.ArrayList;

import user.ModuleManager;
import user.Student;

/**
 * 
 * This class contains all methods and attributes linked to the
 * Modules dispenced in the University
 * 
 * @author Sébastien HERT
 * 
 */
public abstract class Module{

    private String name;
    private TeachingUnit unit;
    private ModuleManager manager;
    private ArrayList<Student> attendees;

    public Module(){}

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return TeachingUnit return the unit
     */
    public TeachingUnit getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(TeachingUnit unit) {
        this.unit = unit;
    }

    /**
     * @return ModuleManager return the manager
     */
    public ModuleManager getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(ModuleManager manager) {
        this.manager = manager;
    }

    /**
     * @return ArrayList<Student> return the attendees
     */
    public ArrayList<Student> getAttendees() {
        return attendees;
    }

    /**
     * @param attendees the attendees to set
     */
    public void setAttendees(ArrayList<Student> attendees) {
        this.attendees = attendees;
    }

    public String toString() {
        return name;
    }

}