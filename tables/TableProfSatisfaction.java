package tables;


public class TableProfSatisfaction {

    
    private String rate;
    private String comments;

    public String getRate() {
        return this.rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public TableProfSatisfaction(String rate, String comments){
       this.rate = rate;
       this.comments = comments;
    }

    }




