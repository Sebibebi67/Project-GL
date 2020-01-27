package tables;


public class TableAbsencesModule {

    
    private String date;
    private String justification;
    
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJustification() {
        return this.justification;
    }
    
    public void setJustification(String justification) {
        this.justification = justification;
    }

    public TableAbsencesModule(String date, String justification){
       this.date = date;
       this.justification = justification;
    }

    }




