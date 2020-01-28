package tables;


public class TableGradesModule {

    String nameGrade, grade;

    public String getNameGrade() {
        return nameGrade;
    }

    public void setNameGrade(String nameGrade) {
        this.nameGrade = nameGrade;

    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public TableGradesModule(String nameGrade, String grade){
       this.grade = grade;
       this.nameGrade = nameGrade;
    }

    }




