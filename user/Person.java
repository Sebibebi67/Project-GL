package user;

import java.util.ArrayList;

public class Person{

    private ArrayList<Role> roles;

    private String surname;
    private String firstname;

    public Person(){
        roles = new ArrayList<Role>();
    }

    /**
     * @return ArrayList<Role> return the roles
     */
    public ArrayList<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return String return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return String return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String toString() {
        return surname + " " + firstname;
    }
}