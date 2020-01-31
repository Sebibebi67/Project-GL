package tables;


public class TableNewGrade {

    String name,surname,id, grade,testName,coefficient;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TableNewGrade(String name, String surname, String grade, String testName, String coefficient, String id){
        this.name = name;
        this.coefficient=coefficient;
        this.grade=grade;
        this.surname=surname;
        this.testName=testName;
        this.id=id;
    }

    }
