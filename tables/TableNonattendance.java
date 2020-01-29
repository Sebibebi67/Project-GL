package tables;


public class TableNonattendance {

    String name,surname,id,module,date,justification;

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String prenom) {
        this.surname = prenom;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String identifiant) {
        this.surname = identifiant;
    }




    public TableNonattendance(String name, String surname, String id, String module, String date, String justification){


        this.name = name;
        this.surname = surname;
        this.id= id;
        this.module = module;
        this.date = date;
        this.justification = justification;
    }

    }
