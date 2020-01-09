import com.mysql.cj.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

public class SQL{

    public static void inscrire(String nom, String prenom, String mdp, String role){
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
            CallableStatement call = conn.prepareCall("{call inscrire(?,?,?,?)}");
            call.setString("nom",nom);
            call.setString("prenom",prenom);
            call.setString("mdp",mdp);
            call.setString("role",role);
            call.execute();
            statement.close();

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
    }

    public static void creerUE(String nomUE, String loginRespo, int nbCredits, String filiere){
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
            CallableStatement call = conn.prepareCall("{call creerUE(?,?,?,?)}");
            call.setString("nomUE",nomUE);
            call.setString("loginRespo",loginRespo);
            call.setInt("nbCredits",nbCredits);
            call.setString("filiere",filiere);
            call.execute();
            statement.close();

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
    }

    public static void creerModule(String nomModule, String loginRespo){
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
            CallableStatement call = conn.prepareCall("{call creerModule(?,?)}");
            call.setString("nomModule",nom);
            call.setString("loginRespo",loginRespo);
            call.execute();
            statement.close();

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
    }

    public static void constitue(String nomModule, String titreUE, int coefficient, String fil){
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
            CallableStatement call = conn.prepareCall("{call constitue(?,?,?,?)}");
            call.setString("nomModule",nomModule);
            call.setString("titreUE",titreUE);
            call.setInt("coefficient",coefficient);
            call.setString("fil",fil);
            call.execute();
            statement.close();

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
    }

    public static void enseigne(String loginU, String nomModule, String role, int idGroupe){ // forcer idGroupe = 0 si role == CM dans le menu deroulant
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
            CallableStatement call = conn.prepareCall("{call enseigne(?,?,?,?)}");
            call.setString("loginU",loginU);
            call.setString("nomModule",nomModule);
            call.setString("role",role);
            call.setInt("idGroupe",idGroupe);
            call.execute();
            statement.close();

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
    }

    public static void assiste(String loginU, String nomModule){
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
            CallableStatement call = conn.prepareCall("{call assiste(?,?)}");
            call.setString("loginU",loginU);
            call.setString("nomModule",nomModule);
            call.execute();
            statement.close();

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
    }

    public static void assignerGroupeTD(int numero, String loginEtu){
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
            CallableStatement call = conn.prepareCall("{call assignerGroupeTD(?,?)}");
            call.setInt("numero",numero);
            call.setString("loginEtu",loginEtu);
            call.execute();
            statement.close();

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
    }

    public static void assignerGroupeTP(int numero, String loginEtu){
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
            CallableStatement call = conn.prepareCall("{call assignerGroupeTP(?,?)}");
            call.setInt("numero",numero);
            call.setString("loginEtu",loginEtu);
            call.execute();
            statement.close();

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
    }

    public static void creerAbsence(String loginEtu, String loginEns, String nomModule, String fil, Date dateDebut, Date dateFin, Time heureDebut, Time heureFin){
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
            CallableStatement call = conn.prepareCall("{call creerAbsence(?,?,?,?,?,?,?,?)}");
            call.setString("loginEtu",loginEtu);
            call.setString("loginEns",loginEns);
            call.setString("nomModule",nomModule);
            call.setString("fil",fil);
            call.setDate("dateDebut",dateDebut);
            call.setDate("dateFin",dateFin);
            call.setTime("heureDebut",heureDebut);
            call.setTime("heureFin",heureFin);
            call.execute();
            statement.close();

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
    }

    public static void modifierFiliereEtudiant(String newFiliere, String loginEtu){
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
            CallableStatement call = conn.prepareCall("{call modifierFiliereEtudiant(?,?)}");
            call.setString("newFiliere",newFiliere);
            call.setString("loginEtu",loginEtu);
            call.execute();
            statement.close();

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
    }

    public static void note(String nomNote, int valeur, int coeff, Date jour, String loginU, String nomModule){
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
            CallableStatement call = conn.prepareCall("{call note(?,?,?,?,?,?)}");
            call.setString("nomNote",nomNote);
            call.setInt("valeur",valeur);
            call.setInt("coeff",coeff);
            call.setDate("jour",jour);
            call.setString("loginU",loginU);
            call.setString("nomModule",nomModule);
            call.execute();
            statement.close();

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
    }

    public static void satisfaction(String loginU, String nomModule, String questionnaire, int note){
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
            CallableStatement call = conn.prepareCall("{call satisfaction(?,?,?,?)}");
            call.setString("loginU",loginU);
            call.setString("nomModule",nomModule);
            call.setText("questionnaire",questionnaire);
            call.setInt("note",note);
            call.execute();
            statement.close();

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
    }

    public static void supprimer(String loginU){
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
            CallableStatement call = conn.prepareCall("{call supprimer(?)}");
            call.setString("loginU",loginU);
            call.execute();
            statement.close();

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
    }

    public static void supprUE(String nom, String fil){
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
            CallableStatement call = conn.prepareCall("{call supprUE(?,?)}");
            call.setString("nom",nom);
            call.setString("fil",fil);
            call.execute();
            statement.close();

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
    }

}