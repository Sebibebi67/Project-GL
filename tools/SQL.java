package tools;

import java.sql.*;

/**
 * 
 * This class is composed of methods used to interact with the database
 * @author Adam RIVIERE 
 * 
 */

public class SQL{

    private static String url       = "jdbc:mysql://barn-e-01:3306";
    private static String user      = "1920_INFO2_PDB";
    private static String password  = "MeWNxYu6xnUGh4DTza6aP4DVKv6Wh9yT"; 

    /**
    * Create a user
    * @author Adam RIVIERE 
    * @param String nom surname of the user
    * @param String prenom name of the user
    * @param String mdp password of the user
    * @param String role role of the user
    */
    public static void inscrire(String nom, String prenom, String mdp, String role){
        Connection conn = null;
        try {
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

    /**
    * Create a unit
    * @author Adam RIVIERE 
    * @param String nomUE name of the unit
    * @param String loginRespo login of the teacher responsible for this unit
    * @param int nbCredits number of credits allowed to this unit
    * @param String filiere sector of the unit
    */
    public static void creerUE(String nomUE, String loginRespo, int nbCredits, String filiere){
        Connection conn = null;
        try {
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

    /**
    * Create a module
    * @author Adam RIVIERE 
    * @param String nomModule name of the module
    * @param String loginRespo login of the teacher responsible for this module
    */
    public static void creerModule(String nomModule, String loginRespo){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
            Statement statement = conn.createStatement();
            CallableStatement call = conn.prepareCall("{call creerModule(?,?)}");
            call.setString("nomModule",nomModule);
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

    /**
    * Assign a module to a unit
    * @author Adam RIVIERE 
    * @param String nomModule name of the module
    * @param String titreUE name of the unit
    * @param int coefficient coefficient of the module in the unit
    * @param String fil sector of the module
    */
    public static void constitue(String nomModule, String titreUE, int coefficient, String fil){
        Connection conn = null;
        try {
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

    /**
    * Assign a teacher to a class
    * @author Adam RIVIERE 
    * @param String loginU login of the user
    * @param String nomModule name of the module
    * @param String role type of class (CM/TP/TD)
    * @param int idGroupe number of the group
    */
    public static void enseigne(String loginU, String nomModule, String role, int idGroupe){ // forcer idGroupe = 0 si role == CM dans le menu deroulant
        Connection conn = null;
        try {
            int idG = idGroupe;
            if(role == "CM"){ // no need of group id if the module is for the entire class
                idG = 0;
            }
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
            Statement statement = conn.createStatement();
            CallableStatement call = conn.prepareCall("{call enseigne(?,?,?,?)}");
            call.setString("loginU",loginU);
            call.setString("nomModule",nomModule);
            call.setString("role",role);
            call.setInt("idGroupe",idG);
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

    /**
    * Assign a student to a class
    * @author Adam RIVIERE 
    * @param String loginU login of the user
    * @param String nomModule name of the module
    */
    public static void assiste(String loginU, String nomModule){
        Connection conn = null;
        try {
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

    /**
    * Assign a student to a group
    * @author Adam RIVIERE 
    * @param int numero number of the group
    * @param String loginEtu login of the student
    */
    public static void assignerGroupeTD(int numero, String loginEtu){
        Connection conn = null;
        try {
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

    /**
    * Assign a student to a group
    * @author Adam RIVIERE 
    * @param int numero number of the group
    * @param String loginEtu login of the student
    */
    public static void assignerGroupeTP(int numero, String loginEtu){
        Connection conn = null;
        try {
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

    // /**
    // * Add an absence in a module for a student
    // * @author Adam RIVIERE 
    // * @param String loginEtu login of the student
    // * @param String loginEns login of the teacher
    // * @param String nomModule name of the module
    // * @param String fil sector of the student
    // * @param Date dateDebut day of the begining of the absence
    // * @param Date dateFin day of the end of the absence
    // * @param Time heureDebut hour of the begining of the absence
    // * @param Time heureFin hour of the end of the absence
    // */
    // public static void creerAbsence(String loginEtu, String loginEns, String nomModule, String fil, Date dateDebut, Date dateFin, Time heureDebut, Time heureFin){
    //     Connection conn = null;
    //     try {
    //         // create a connection to the database
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //         conn = DriverManager.getConnection(url, user, password);
            
    //         Statement statement = conn.createStatement();
    //         CallableStatement call = conn.prepareCall("{call creerAbsence(?,?,?,?,?,?,?,?)}");
    //         call.setString("loginEtu",loginEtu);
    //         call.setString("loginEns",loginEns);
    //         call.setString("nomModule",nomModule);
    //         call.setString("fil",fil);
    //         call.setDate("dateDebut",dateDebut);
    //         call.setDate("dateFin",dateFin);
    //         call.setTime("heureDebut",heureDebut);
    //         call.setTime("heureFin",heureFin);
    //         call.execute();
    //         statement.close();

    //     } catch(SQLException | ClassNotFoundException e) {
    //         e.printStackTrace();
    //     } finally {
    //         try{
    //             if(conn != null){
    //                 conn.close();
    //             }
    //         }catch(SQLException ex){
    //             ex.printStackTrace();
    //         }
    //     }
    // }

    /**
    * Creates a new absence in the database.
    * @author Adam RIVIERE
    * @param Time beginHour hour of the begining of the absence
    * @param Time endHour hour of the end of the absence
    * @param String moduleName Module's name
    * @param String loginS login of the absent student
    * @param String loginP login of the professor
    * @param Date date of the absence
    */
    public static void createAbsence(Time beginHour, Time endHour, String moduleName, String loginS, String loginP, Date date){
        Connection conn = null;
        int idModule = Query.getModuleID(moduleName);
        int idStudent = Query.getStudentID(loginS);
        int idProfessor = Query.getTeacherID(loginP);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "INSERT INTO Absence(idEtudiant, idEnseignant, idModule, dateDebut, dateFin, heureDebut, heureFin, estJustifiee) VALUES("+idStudent+","+idProfessor+","+idModule+","+date+","+date+","+beginHour+","+endHour+","+false+");";
            statement.executeQuery(query);
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

    /**
    * Change the sector of a student
    * @author Adam RIVIERE  
    * @param String newFiliere new sector of the student
    * @param String loginEtu login of the student
    */
    public static void modifierFiliereEtudiant(String newFiliere, String loginEtu){
        Connection conn = null;
        try {
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

    /**
    * Add a grade to a student
    * @author Adam RIVIERE  
    * @param String nomNote name of the evaluation
    * @param int valeur grade of the student
    * @param int coeff coefficient of the evaluation
    * @param Date date of the evaluation
    * @param String loginU login of the student
    * @param String nomModule name of the module
    */
    public static void note(String nomNote, int valeur, int coeff, Date jour, String loginU, String nomModule){
        Connection conn = null;
        try {
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

    /**
    * Add a grade to a module
    * @author Adam RIVIERE 
    * @param String loginU login of the student
    * @param String nomModule name of the module
    * @param String questionnaire comments on the module
    * @param int note grade of the module
    */
    public static void satisfaction(String loginU, String nomModule, String questionnaire, int note){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
            Statement statement = conn.createStatement();
            CallableStatement call = conn.prepareCall("{call satisfaction(?,?,?,?)}");
            call.setString("loginU",loginU);
            call.setString("nomModule",nomModule);
            call.setString("questionnaire",questionnaire);
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

    /**
    * Delete a user
    * @author Adam RIVIERE 
    * @param String loginU login of the user
    */
    public static void supprimer(String loginU){
        Connection conn = null;
        try {
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

    /**
    * Delete a unit
    * @author Adam RIVIERE 
    * @param String nom name of the unit
    * @param String fil sector of the unit
    */
    public static void supprUE(String nom, String fil){
        Connection conn = null;
        try {
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