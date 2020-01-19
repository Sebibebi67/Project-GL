package tools;

import user.*;

public class Main{

    public static void main(String[] args) {
        Test.initErrorLogin();
        Test.initErrorPswd();
    }

    public static void createPerson(String pswd, String login){
        Person person = new Person(login, pswd);
        Stockage.setPerson(person);
    }
}