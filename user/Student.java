package user;

import admin.*;
import study.*;

public class Student implements Role{

    private String login;

    private EduForm form;
    private Group td;
    private Group tp;
    private Course course;


    public Student(){
        
    }

    public Student(String login){
        this.login = login;
        this.getData(login);

    }

    public void getData(String login){
        
    }


}