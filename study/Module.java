package study;

import java.util.ArrayList;

import tools.Query;

/**
 * 
 * This class contains all methods and attributes linked to the
 * Modules dispensed in the University
 * 
 * @author Sébastien HERT
 * 
 */
public class Module{

    private String name;
    private TeachingUnit unit;
    // private ArrayList<Student> attendees;
    private int coeff;
    private ArrayList<Satisfaction> listSatisfaction;

    public int getCoeff() {
        return this.coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    public ArrayList<Satisfaction> getlistSatisfaction(){
        return this.listSatisfaction;
    }

    public void setlistSatisfaction(ArrayList<Satisfaction> list){
        this.listSatisfaction = list;
    }

    public Module(){}

    public Module(String name){
        this.name= name;
        ArrayList<Object> array = new ArrayList<>();
        array = Query.coeffInTU(name);

        unit = new TeachingUnit(array.get(0).toString());
        coeff = (int) array.get(1);
        listSatisfaction = getSatisfaction(name);
    }

    public ArrayList<Satisfaction> getSatisfaction(String moduleName){
        ArrayList<Satisfaction> array = new ArrayList<Satisfaction>();
        ArrayList<ArrayList<?>> queryResult = new ArrayList<ArrayList<?>>();
        queryResult = Query.allModuleSatisfactions(moduleName);
        for(int i = 0;i < queryResult.get(0).size();i++){
            Satisfaction satisfaction = new Satisfaction(moduleName,(int) queryResult.get(0).get(i),queryResult.get(1).get(i).toString());
            array.add(satisfaction);
        }
        return array;
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

    public boolean equals(Module module)
    {
        return this.name.equals(module.getName());
    }

}