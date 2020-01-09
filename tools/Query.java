package tools;

import tools.com.mysql.cj.*;
import java.sql.*;

public class Query{

    public static void exams(int idModule, int loginEtu){
        Connection conn = null;
        try {
            // db parameters
            String url       = "jdbc:mysql://localhost:3306/PPDBDD";
            String user      = "test";
            String password  = "azerty";
            
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nomNote, note, coefficient FROM Note WHERE idModule = ? AND idEtudiant = ?;";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                //System.out.println("test4");
                String nom = res.getString("nomUE");
                String filiere = res.getString("filiere");
                String login = res.getString("login");
                System.out.println(nom+" "+filiere+" "+login);
            }

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                //statement.close();
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }

    }

}