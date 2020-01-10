package study;

import user.TUManager;

/**
 * 
 * This class contains all methods and attributes linked to a Teaching unit
 * made of modules and which are included in the schedule for a graduating class.
 * @author Sébastien HERT 
 * 
 */
public abstract class TeachingUnit {

    private String name;
    private TUManager manager;
    private String course;

    public TeachingUnit() {}


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
     * @return TUManager return the manager
     */
    public TUManager getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(TUManager manager) {
        this.manager = manager;
    }

    /**
     * @return String return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    public String toString() {
        return name;
    }

}