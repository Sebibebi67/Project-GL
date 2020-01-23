package study;

/**
 * 
 * This class contains all methods and attributes linked to a Course
 * 
 * @author Sébastien HERT
 * @author Dejan PARIS
 * 
 */

public class Course{

    private String name;

    /**
     * @return the name of the course
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name name of the course to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructor
     */
    public Course(){}

    /**
     * Gets the year of the course
     * @author Dejan PARIS
     * @return the year of the course
     */
    public int year()
    {
        int year;
        String endChar = this.name.substring(this.name.length()-1, this.name.length());
        if (new String("3").equals(endChar))
        {
            year = 3;
        } else if (new String("2").equals(endChar))
        {
            year = 2;
        } else
        {
            year = 1;
        }
        return year;
    }

    /**
     * @return the name of the course
     */
    public String toString()
    {
        return this.name;
    }
    
    /**
     * Constructor
     * @param s name of the course
     */
    public Course(String s){
        this.name = s;
    }

    /**
     * Tests if a course is equal to an other one
     * @param course course to be tested
     * @return true or false
     */
    public boolean equals(Course course)
    {
        return this.name.equals(course.getName());
    }
}