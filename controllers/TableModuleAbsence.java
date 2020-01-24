package controllers;


public class TableModuleAbsence {

    String date;

    String justification;

    public String getJustification() {
        return justification;
    }

    public void setJusitifcation(String justification) {
        this.justification = justification;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TableModuleAbsence(String date, String justification){
        this.date = date;
        this.justification = justification;
    }

    }




