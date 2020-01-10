package study;

import java.util.ArrayList;

import tools.Query;

/**
 * 
 * This class contains all methods and attributes linked to the
 * Modules dispenced in the University
 * 
 * @author Sébastien HERT
 * 
 */
public class Module{

    private String name;
    private TeachingUnit unit;
    // private ArrayList<Student> attendees;
    private int coeff;

    public int getCoeff() {
        return this.coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    public Module(){}

    public Module(String name){
        this.name= name;
        ArrayList<Object> array = new ArrayList<>();
        array = Query.coeffInTU(name);

        unit = new TeachingUnit(array.get(0).toString());
        coeff = (int) array.get(1);
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

    // /**
    //  * @return ArrayList<Student> return the attendees
    //  */
    // public ArrayList<Student> getAttendees() {
    //     return attendees;
    // }

    // /**
    //  * @param attendees the attendees to set
    //  */
    // public void setAttendees(ArrayList<Student> attendees) {
    //     this.attendees = attendees;
    // }

    public String toString() {
        return name;
    }

}