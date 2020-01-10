package study;

public class Course{

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course(){}

    public Course(String s){
        this.name = s;
    }
}