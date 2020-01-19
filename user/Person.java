package user;

import tools.*;
import java.util.ArrayList;

public class Person{

    private Role role;

    private String surname;
    private String firstname;

    private UserAccount account;

    public Person(){}

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

    public UserAccount getAccount() {
        return this.account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public String toString() {
        return surname + " " + firstname;
    }

    public boolean equals(Person person)
    {
        return this.account.equals(person.getAccount());
    }

    public void getData(String login, String pswd){
        ArrayList<Object> array = new ArrayList<>();
        array = Query.userData(login);
        
        if(array.get(1).toString().equals(pswd)){
            this.account = new UserAccount(login, pswd);
            this.surname = array.get(2).toString();
            this.firstname = array.get(3).toString();
            String roleType = array.get(4).toString();

            switch(roleType){
                case "Etudiant":
                    role = new Student(login);
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


        }



    }
}