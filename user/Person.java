package user;

import tools.*;
import java.util.ArrayList;

public class Person{

    private ArrayList<Role> roles;

    private String surname;
    private String firstname;

    private UserAccount account;

    public UserAccount getAccount() {
        return this.account;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }

    public Person(){
        roles = new ArrayList<Role>();
    }

    public Person(String login, String pswd){
        roles = new ArrayList<Role>();
        this.getData(login, pswd);        
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
                    roles.add(new Student(login));
                    break;
                case "Enseignant":
                    roles.add(new Professor());
                    break;
                case "Scolarite":
                    roles.add(new StudentOffice());
                    break;
                case "DDE":
                    roles.add(new DepartementOfEducation());
                    break;
                default :
                    break;
            }


        }



    }
}