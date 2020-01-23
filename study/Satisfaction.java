package study;


/**
 * 
 * This class contains all methods and attributes linked to Satisfaction answers
 * given by students to a Module
 * 
 * @author Sébastien HERT
 * 
 */
public class Satisfaction{

    private String loginStudent;
    private String moduleName;
    private int rating;
    private String review;

    /**
     * Constructor
     */
    public Satisfaction(){}

    /**
     * Constructor
     * @param login login of the student
     * @param moduleName name of the module
     * @param rating mark of satisfaction
     * @param review review of satisfaction
     */
    public Satisfaction(String login, String moduleName, int rating, String review){
        this.loginStudent = login;
        this.moduleName = moduleName;
        this.rating = rating;
        this.review = review;
    }

    /**
     * Constructor
     * @param moduleName name of the module
     * @param rating mark of satisfaction
     * @param review review of satisfaction
     */
    public Satisfaction(String moduleName, int rating, String review){
        this.loginStudent = null;
        this.moduleName = moduleName;
        this.rating = rating;
        this.review = review;
    }

    /**
     * @return Student return the student
     */
    public String getloginStudent() {
        return loginStudent;
    }

    /**
     * @param student the student to set
     */
    public void setloginStudent(String student) {
        this.loginStudent = student;
    }

    /**
     * @return Module return the module
     */
    public String getmoduleName() {
        return moduleName;
    }

    /**
     * @param module the module to set
     */
    public void setmoduleName(String module) {
        this.moduleName = module;
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

    /**
     * @return the name of the student, the name of the module, the mark and the review
     */
    public String toString() {
        return loginStudent + " rated " + moduleName + " " + Integer.toString(rating) + " stars and wrote : " + review;
    }

}