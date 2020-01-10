package study;

import user.Student;

/**
 * 
 * This class contains all methods and attributes linked to Satisfaction answers
 * given by students to a Module
 * 
 * @author Sébastien HERT
 * 
 */
public class Satisfaction{

    private Student student;
    private Module module;
    private int rating;
    private String review;

    public Satisfaction(){}

    /**
     * @return Student return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
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
     * @return int return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return String return the review
     */
    public String getReview() {
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(String review) {
        this.review = review;
    }

    public String toString() {
        return student.toString() + " rated " + module.toString() + " " + Integer.toString(rating) + " stars and wrote : " + review;
    }

}