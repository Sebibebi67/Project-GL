package study;

import java.sql.Date;
import java.util.ArrayList;

import user.ExamManager;

/**
 * 
 * This class contains all methods and attributes linked to an Exam
 * 
 * @author Sébastien HERT
 * 
 */
public class Exam{

    private String name;
    private Date date;
    private Module module;
    private int coeff;
    private ExamManager manager;
    private ArrayList<String> attendees;
    
    public Exam(){}

    public Exam(String name, Date date, int coeff, Module module){
        this.name = name;
        this.date = date;
        this.coeff = coeff;
        this.module = module;

    }

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
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
     * @return Module return the module
     */
    public Module getModule() {
        return module;
    }

    /**
     * @param module the module to set
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * @return ExamManager return the manager
     */
    public ExamManager getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(ExamManager manager) {
        this.manager = manager;
    }
    
    public int getCoeff() {
        return this.coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    /**
     * @return ArrayList<String> return the attendees
     */
    public ArrayList<String> getAttendees() {
        return attendees;
    }

    /**
     * @param attendees the attendees to set
     */
    public void setAttendees(ArrayList<String> attendees) {
        this.attendees = attendees;
    }

    public String toString() {
        return name + " : " + date.toString();
    }
}