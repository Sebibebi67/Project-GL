package user;

import tools.*;
import java.util.ArrayList;

/**
 * 
 * This class contains all methods and attributes linked to a Person
 * 
 * @author Sébastien HERT
 * @author Dejan PARIS
 * 
 */

public class Person{

    private Role role;

    private String surname;
    private String firstname;

    private UserAccount account;

    /**
     * Constructor
     */
    public Person(){}

    /**
     * Constructor
     * @param login login of the user
     * @param pswd password of the user
     */
    public Person(String login, String pswd){
        this.getData(login, pswd);        
    }

    /**
     * @return Role return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
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

    /**
     * @return the account linked to the user
     */
    public UserAccount getAccount() {
        return this.account;
    }

    /**
     * @param account acount to set
     */
    public void setAccount(UserAccount account) {
        this.account = account;
    }

    /**
     * Prints the name and surname of a user
     * @author Sébastien HERT
     * @return a string with the name and the surname
     */
    public String toString() {
        return surname + " " + firstname;
    }

    /**
     * Tests if a person is equal to an other one
     * @author Dejan PARIS
     * @param person  person to test
     * @return true or false
     */
    public boolean equals(Person person)
    {
        return this.account.equals(person.getAccount());
    }

    /**
     * Gets the data of the user from the database
     * @author Sébastien HERT
     * @param login login of the user
     * @param pswd password of the user
     */
    public void getData(String login, String pswd){
        ArrayList<Object> array = new ArrayList<>();
        array = Query.userData(login);
        if (array.isEmpty()){
            Tool.print("Person not exists");
        }else{
            
            if(array.get(1).toString().equals(pswd)){
                this.account = new UserAccount(login, pswd);
                this.surname = array.get(2).toString();
                this.firstname = array.get(3).toString();
                String roleType = array.get(4).toString();

                switch(roleType){
                    case "Etudiant":
                        role = new Student(login);
                        Stockage.setStudent((Student) role);
                        break;
                    case "Enseignant":
                        role = new Professor(login);
                        break;
                    case "Scolarite":
                        role = new StudentOffice(login);
                        break;
                    case "DDE":
                        role = new DepartementOfEducation(login);
                        break;
                    default :
                        break;
                }
            }else{
                Tool.print("Wrong pswd");
            }

        }



    }
}