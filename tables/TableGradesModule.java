package tables;


public class TableGradesModule {

    String nomNote, note;

    public String getNomNote() {
        return nomNote;
    }

    public void setNomNote(String nomNote) {
        this.nomNote = nomNote;

    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TableGradesModule(String nomNote, String note){
       this.note = note;
       this.nomNote = nomNote;
    }

    }




