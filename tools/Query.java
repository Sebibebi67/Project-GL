package tools;

//import tools.com.mysql.cj.*;
import java.sql.*;
import java.util.ArrayList;

public class Query{

    private static String url       = "jdbc:mysql://localhost:3306/PPDBDD";
    private static String user      = "test";
    private static String password  = "azerty"; 

    public static int getStudentID(String loginEtu){
        Connection conn = null;
        int id = -1;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT idEtudiant FROM Etudiant JOIN Utilisateur ON Etudiant.idUtilisateur = Utilisateur.idUtilisateur WHERE login = "+loginEtu+";";
            ResultSet res = statement.executeQuery(query);
            id = res.getInt("idEtudiant");

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return id;
    }

    public static int getModuleID(String nomModule){
        Connection conn = null;
        int id = -1;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT idModule FROM Module WHERE nomModule = "+nomModule+";";
            ResultSet res = statement.executeQuery(query);
            id = res.getInt("idEtudiant");

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return id;
    }

    public static ArrayList<Object> exams(int idModule, String loginEtu){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        int idEtudiant = getStudentID(loginEtu); 
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nomNote, note, coefficient FROM Note WHERE idModule ="+idModule+" AND idEtudiant = "+idEtudiant+";";
            ResultSet res = statement.executeQuery(query);
            ArrayList<String> nomNote = new ArrayList<String>();
            ArrayList<Integer> note = new ArrayList<Integer>();
            ArrayList<Integer> coefficient = new ArrayList<Integer>();
            while(res.next()){
                nomNote.add(res.getString("nomNote"));
                note.add(res.getInt("note"));
                coefficient.add(res.getInt("coefficient"));
            }
            queryResult.add(nomNote);
            queryResult.add(note);
            queryResult.add(coefficient);

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    public static ArrayList<Float> studentAverage(int idModule, String loginEtu){
        Connection conn = null;
        ArrayList<Float> average = new ArrayList<Float>();
        int idEtudiant = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT SUM(note*coefficient)/SUM(coefficient) AS average FROM Note WHERE idModule = "+idModule+" AND idEtudiant = "+idEtudiant+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                average.add(res.getFloat("average"));
            }

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return average;
    }

    public static ArrayList<Integer> mark(String nomNote, String loginEtu){
        Connection conn = null;
        ArrayList<Integer> mark = new ArrayList<Integer>();
        int idEtudiant = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT note FROM Note WHERE nomNote = "+nomNote+" AND idEtudiant = "+idEtudiant+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                mark.add(res.getInt("note"));
            }

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return mark;
    }

    public static ArrayList<String> courses(String loginEtu){
        Connection conn = null;
        ArrayList<String> courses = new ArrayList<String>();
        int idEtudiant = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT DISTINCT nomModule FROM Module WHERE idModule IN (SELECT idModule FROM Assiste WHERE idEtudiant = "+idEtudiant+");";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                courses.add(res.getString("note"));
            }

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return courses;
    }

    public static ArrayList<Object> absence(String loginEtu){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        int idEtudiant = getStudentID(loginEtu); 
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT dateDebut, heureDebut, dateFin, heureFin, estJustifiee FROM absences WHERE idEtudiant = "+idEtudiant+";";
            ResultSet res = statement.executeQuery(query);
            ArrayList<Date> dateDebut = new ArrayList<Date>();
            ArrayList<Integer> heureDebut = new ArrayList<Integer>();
            ArrayList<Date> dateFin = new ArrayList<Date>();
            ArrayList<Integer> heureFin = new ArrayList<Integer>();
            ArrayList<Boolean> estJustifiee = new ArrayList<Boolean>();
            while(res.next()){
                dateDebut.add(res.getDate("dateDebut"));
                heureDebut.add(res.getInt("heureDebut"));
                dateFin.add(res.getDate("dateFin"));
                heureFin.add(res.getInt("heureFin"));
                estJustifiee.add(res.getBoolean("estJustifiee"));
            }
            queryResult.add(dateDebut);
            queryResult.add(heureDebut);
            queryResult.add(dateFin);
            queryResult.add(heureFin);
            queryResult.add(estJustifiee);

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    public static ArrayList<Object> results(String nomNote){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nom, prenom, note, coefficient FROM Note, Utilisateur JOIN Etudiant ON Etudiant.idEtudiant=Note.idEtudiant AND Etudiant.idUtilisateur=Utilisateur.idUtilisateur WHERE nomNote = "+nomNote+";";
            ResultSet res = statement.executeQuery(query);
            ArrayList<String> nom = new ArrayList<String>();
            ArrayList<String> prenom = new ArrayList<String>();
            ArrayList<Integer> note = new ArrayList<Integer>();
            ArrayList<Integer> coefficient = new ArrayList<Integer>();
            while(res.next()){
                nom.add(res.getString("nom"));
                prenom.add(res.getString("prenom"));
                note.add(res.getInt("note"));
                coefficient.add(res.getInt("coefficient"));
            }
            queryResult.add(nom);
            queryResult.add(prenom);
            queryResult.add(note);
            queryResult.add(coefficient);

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    public static ArrayList<Float> courseAverage(String nomModule){
        Connection conn = null;
        ArrayList<Float> average = new ArrayList<Float>();
        int idModule = getModuleID(nomModule);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT SUM(note*coefficient)/SUM(coefficient) AS average FROM Note WHERE idModule = "+idModule+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                average.add(res.getFloat("average"));
            }

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return average;
    }

    public static ArrayList<Float> examAverage(String nomNote, String nomModule){
        Connection conn = null;
        ArrayList<Float> average = new ArrayList<Float>();
        int idModule = getModuleID(nomModule);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT SUM(note*coefficient)/SUM(coefficient) AS average FROM Note WHERE nomNote = "+nomNote+" AND idModule = "+idModule+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                average.add(res.getFloat("average"));
            }

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return average;
    }

    public static ArrayList<Float> satisfactionAverage(String nomModule){
        Connection conn = null;
        ArrayList<Float> average = new ArrayList<Float>();
        int idModule = getModuleID(nomModule);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT AVG(noteSatisfaction) as average FROM Satisfaction WHERE idModule = "+idModule+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                average.add(res.getFloat("average"));
            }

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return average;
    }

    public static ArrayList<Object> attendees(int idModule){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT idEtudiant FROM Etudiant JOIN Assiste ON Assiste.idEtudiant = Etudiant.idEtudiant WHERE idModule = "+idModule+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                queryResult.add(res.getInt("idEtudiant"));
            }

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

}