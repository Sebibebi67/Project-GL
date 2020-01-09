package tools;

//import tools.com.mysql.cj.*;
import java.sql.*;
import java.util.ArrayList;

public class Query{

    private static String url       = "jdbc:mysql://localhost:3306/PPDBDD";
    private static String user      = "test";
    private static String password  = "azerty"; 

    public static ArrayList<Object> exams(int idModule, String loginEtu){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nomNote, note, coefficient FROM Note WHERE idModule ="+idModule+" AND idEtudiant = "+loginEtu+";";
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

}