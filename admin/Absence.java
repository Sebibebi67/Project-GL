package admin;

import java.sql.Date;
import java.sql.Time;

import user.Student;
import user.Professor;

/**
 * 
 * This class contains all the methods and attributs linked with the management of absences
 * @author Sébastien HERT 
 * 
 */

public class Absence {

	private Date beginDate;
	private Date endDate;
	private Time beginHour;
	private Time endHour;
	private Student student;
	private Module module;
    private Professor teacher;
    private Boolean justified;



	public Absence () {

    }
    
    public Absence (Date beginDate, Time beginHour, Date endDate, Time endHour, Boolean justified) {
        this.beginDate = beginDate;
        this.beginHour = beginHour;

        this.endDate = endDate;
        this.endHour = endHour;


        this.justified = justified;
    }
    
    public Absence(Date date, Time beginHour, Time endHour, Student student, Professor professor){
        this.beginDate = date;
        this.endDate = date;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.student = student;
        this.teacher = professor;
    }


    /**
     * @return Date return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return Date return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return Time return the beginHour
     */
    public Time getBeginHour() {
        return beginHour;
    }

    /**
     * @param beginHour the beginHour to set
     */
    public void setBeginHour(Time beginHour) {
        this.beginHour = beginHour;
    }

    /**
     * @return Time return the endHour
     */
    public Time getEndHour() {
        return endHour;
    }

    /**
     * @param endHour the endHour to set
     */
    public void setEndHour(Time endHour) {
        this.endHour = endHour;
    }

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
     * @return Teacher return the teacher
     */
    public Professor getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(Professor teacher) {
        this.teacher = teacher;
	}
	
	public String toString() {
		return student.toString() + " absent from " + module.toString() + " taught by " + teacher.toString() + " from " + beginDate.toString() + " ; " + beginHour.toString() + " to " + endDate.toString() + " ; " + endHour.toString() + ".";
    }
    
    public Boolean getJustified() {
        return this.justified;
    }

    public void isJustified(Boolean justified) {
        this.justified = justified;
    }

}