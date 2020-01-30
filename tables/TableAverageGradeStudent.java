package tables;


public class TableAverageGradeStudent {

    String module,averageGrade,retake;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getRetake() {
        return retake;
    }

    public void setRetake(String retake) {
        this.retake = retake;
    }

    public TableAverageGradeStudent(String module,String averageGrade,String retake){

        this.module = module;
        this.averageGrade = averageGrade;
        this.retake = retake;
    }

}
