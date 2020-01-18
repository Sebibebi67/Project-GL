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

    public int year()
    {
        int year;
        String endChar = this.name.substring(this.name.length()-1, this.name.length());
        if (new String("3").equals(endChar))
        {
            year = 3;
        } else if (new String("2").equals(endChar))
        {
            year = 2;
        } else
        {
            year = 1;
        }
        return year;
    }

    public String toString()
    {
        return this.name;
    }
    
    public Course(String s){
        this.name = s;
    }

    public boolean equals(Course course)
    {
        return this.name.equals(course.name);
    }
}