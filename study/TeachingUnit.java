package study;

import tools.Query;

/**
 * 
 * This class contains all methods and attributes linked to a Teaching unit
 * made of modules and which are included in the schedule for a graduating class.
 * 
 * @author Sébastien HERT 
 * @author Dejan PARIS
 * 
 */
public class TeachingUnit {

    private String name;
    private Course course;

    /**
     * @return the course of the teaching unit
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * @param course course to set
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Constructor
     */
    public TeachingUnit() {}

    /**
     * Constructor
     * @param name name of the teaching unit
     * @see Query.courseOfTU
     */
    public TeachingUnit(String name) {
        this.name = name;
        this.course = new Course(Query.courseOfTU(name));
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
     * @return the name of the teaching unit
     */
    public String toString() {
        return name;
    }

    /**
     * Tests if a teaching unit is equal to an other one
     * @author Dejan PARIS
     * @param unit unit to be tested
     * @return tre or false
     */
    public boolean equals(TeachingUnit unit)
    {
        return this.name.equals(unit.getName()) && this.course.equals(unit.getCourse());
    }

}