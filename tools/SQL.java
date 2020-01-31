package tools;

import java.sql.*;

/**
 * 
 * This class is composed of methods used to interact with the database
 * @author Adam RIVIÈRE 
 * 
 */

public class SQL{

    private static String url       = "jdbc:mysql://barn-e-01:3306";
    private static String user      = "1920_INFO2_PDB";
    private static String password  = "MeWNxYu6xnUGh4DTza6aP4DVKv6Wh9yT"; 

    /**
    * Create a user
    * @author Adam RIVIERE 
    * @param nom surname of the user
    * @param prenom name of the user
    * @param mdp password of the user
    * @param role role of the user
    */
    public static void inscrire(String nom, String prenom, String mdp, String role){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call inscrire(    '"+nom+"','"+
                                                    prenom+"','"+
                                                    mdp+"','"+
                                                    role+"');";
            statement.execute(request);

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
    * @param nomUE name of the unit
    * @param loginRespo login of the teacher responsible for this unit
    * @param nbCredits number of credits allowed to this unit
    * @param filiere sector of the unit
    */
    public static void creerUE(String nomUE, String loginRespo, int nbCredits, String filiere){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call creerUE(    '"+nomUE+"','"+
                                                    loginRespo+"',"+
                                                    nbCredits+",'"+
                                                    filiere+"');";
            statement.execute(request);

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
    * @param nomModule name of the module
    * @param loginRespo login of the teacher responsible for this module
    */
    public static void creerModule(String nomModule, String loginRespo){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call creerModule(    '"+nomModule+"','"+
                                                    loginRespo+"');";
            statement.execute(request);

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
    * @param nomModule name of the module
    * @param titreUE name of the unit
    * @param coefficient coefficient of the module in the unit
    * @param fil sector of the module
    */
    public static void constitue(String nomModule, String titreUE, int coefficient, String fil){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call constitue(    '"+nomModule+"','"+
                                                    titreUE+"',"+
                                                    coefficient+",'"+
                                                    fil+"');";
            statement.execute(request);

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
    * @param loginU login of the user
    * @param nomModule name of the module
    * @param role type of class (CM/TP/TD)
    * @param idGroupe number of the group
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
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call enseigne(    '"+loginU+"','"+
                                                    nomModule+"','"+
                                                    role+"',"+
                                                    idG+");";
            statement.execute(request);

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
    * @param loginU login of the user
    * @param nomModule name of the module
    */
    public static void assiste(String loginU, String nomModule){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call assiste(    '"+loginU+"','"+
                                                    nomModule+"');";
            statement.execute(request);

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
    * @param numero number of the group
    * @param loginEtu login of the student
    */
    public static void assignerGroupeTD(int numero, String loginEtu){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call assignerGroupeTD("+numero+",'"+
                                                    loginEtu+"');";
            statement.execute(request);

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
    * @param numero number of the group
    * @param loginEtu login of the student
    */
    public static void assignerGroupeTP(int numero, String loginEtu){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call assignerGroupeTP("+numero+",'"+
                                                    loginEtu+"');";
            statement.execute(request);

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
    * Creates a new absence in the database.
    * @author Adam RIVIERE
    * @param beginHour hour of the begining of the absence
    * @param endHour hour of the end of the absence
    * @param moduleName Module's name
    * @param loginS login of the absent student
    * @param loginP login of the professor
    * @param date of the absence
    */
    public static void createAbsence(Time beginHour, Time endHour, String moduleName, String loginS, String loginP, Date date){
        Connection conn = null;
        int idModule = Query.getModuleID(moduleName);
        int idStudent = Query.getStudentID(loginS);
        int idProfessor = Query.getTeacherID(loginP);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);

            Statement statement = conn.createStatement();
            String query = "INSERT INTO Absence(idEtudiant, idEnseignant, idModule, dateDebut, dateFin, heureDebut, heureFin, estJustifiee) VALUES("+idStudent+","+idProfessor+","+idModule+","+date+","+date+","+beginHour+","+endHour+","+false+");";
            statement.executeUpdate(query);

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
    * @param newFiliere new sector of the student
    * @param loginEtu login of the student
    */
    public static void modifierFiliereEtudiant(String newFiliere, String loginEtu){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call modifierFiliereEtudiant('"+newFiliere+"','"+
                                                                loginEtu+"');";
            statement.execute(request);

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
    * @param nomNote name of the evaluation
    * @param valeur grade of the student
    * @param coeff coefficient of the evaluation
    * @param loginU login of the student
    * @param nomModule name of the module
    */
    public static void note(String nomNote, int valeur, int coeff, String loginU, String nomModule){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call note('"+nomNote+"',"+
                                                            valeur+","+
                                                            coeff+",'2020-01-01','"+
                                                            loginU+"','"+
                                                            nomModule+"');";
            statement.execute(request);

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
    * @param loginU login of the student
    * @param nomModule name of the module
    * @param questionnaire comments on the module
    * @param note grade of the module
    */
    public static void satisfaction(String loginU, String nomModule, String questionnaire, int note){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String query = "SELECT * from Satisfaction WHERE idEtudiant="+Query.getStudentID(loginU)+" AND idModule="+Query.getModuleID(nomModule)+" ;";

            ResultSet res = statement.executeQuery(query);
            if (res==null){


                String request = "call satisfaction(    '"+loginU+"','"+
                                                        nomModule+"','"+
                                                        questionnaire+"',"+
                                                        note+");";
                statement.execute(request);
            }else{
                System.out.println("error : already exists");
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
    }

    /**
    * Delete a user
    * @author Adam RIVIERE 
    * @param loginU login of the user
    */
    public static void supprimer(String loginU){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call supprimer(    '"+loginU+"');";
            statement.execute(request);

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
    * @param nom name of the unit
    * @param fil sector of the unit
    */
    public static void supprUE(String nom, String fil){
        Connection conn = null;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+user,user,password);
            
            Statement statement = conn.createStatement();
            String request = "call supprUE(    '"+nom+"','"+
                                                fil+"');";
            statement.execute(request);

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