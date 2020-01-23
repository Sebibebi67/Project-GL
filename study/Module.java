package study;

import java.util.ArrayList;

import tools.Query;

/**
 * 
 * This class contains all methods and attributes linked to the
 * Modules dispensed in the University
 * 
 * @author Sébastien HERT
 * @author Dejan PARIS
 * @author Adam RIVIERE
 * 
 */
public class Module{

    private String name;
    private TeachingUnit unit;
    private int coeff;
    private ArrayList<Satisfaction> listSatisfaction;

    /**
     * @return the coefficient of the module
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
     * @return the list of the satisfactions
     */
    public ArrayList<Satisfaction> getlistSatisfaction(){
        return this.listSatisfaction;
    }

    /**
     * @param list list of satisfactions to set
     */
    public void setlistSatisfaction(ArrayList<Satisfaction> list){
        this.listSatisfaction = list;
    }

    /**
     * Constructor
     */
    public Module(){}

    /**
     * Constructor
     * @param name name of the module
     * @see Query.coeffInTU
     */
    public Module(String name){
        this.name= name;
        ArrayList<Object> array = new ArrayList<>();
        array = Query.coeffInTU(name);

        unit = new TeachingUnit(array.get(0).toString());
        coeff = (int) array.get(1);
        listSatisfaction = getSatisfaction(name);
    }

    /**
     * Gets all the marks and reviews of satisfaction for a module
     * @author Sébastien HERT
     * @author Adam RIVIERE
     * @param moduleName name of the module
     * @return the list of the satisfactions
     */
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

    /**
     * @return the name of the module
     */
    public String toString() {
        return name;
    }

    /**
     * Tests if a module is equal to an other one
     * @author Dejan PARIS
     * @param module module to be tested
     * @return true or false
     */
    public boolean equals(Module module)
    {
        return this.name.equals(module.getName());
    }

}