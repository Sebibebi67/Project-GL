package study;

import java.sql.Date;
import java.util.ArrayList;

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
    private ArrayList<String> attendees;
    
    /**
     * Constructor
     */
    public Exam(){}

    /**
     * Constructor
     * @param name name of the exam
     * @param date date of the exam
     * @param coeff coefficient of the exam
     * @param module module of the exam
     */
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
     * @return the coefficient of the exam
     */
    public int getCoeff() {
        return this.coeff;
    }

    /**
     * @param coeff coefficient to set
     */
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

    /**
     * @return the name and the date of the exam
     */
    public String toString() {
        return name + " : " + date.toString();
    }

    /**
     * Tests if an exam is equal to an other one
     * @author Dejan PARIS
     * @param exam exam to be tested
     * @return true or false
     */
    public boolean equals(Exam exam)
    {
        return this.name.equals(exam.getName()) && this.module.equals(exam.getModule());
    }
}