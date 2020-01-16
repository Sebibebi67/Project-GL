package tools;

import user.Person;

public class Stockage{

    private static Person person;
    
    
    public static Person getPerson() {
        return person;
    }

    public static void setPerson(Person p) {
        person = p;
    }

}