package tools;

/**
 * 
 * This class is composed by every test made in the conception
 * @author Sébastien HERT 
 * 
 */
public class Test{

    /**
     * Creates a valid student
     * @author Sébastien HERT
     */
    public static void initStudent(){
        String login = "dparis";
        String pswd = "priority";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());

    }

    /**
     * Creates a valid professor
     * @author Sébastien HERT
     */
    public static void initProfessor(){
        String login = "leuler";
        String pswd = "eipimoins1egal0";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    /**
     * Creates a valid student office
     * @author Sébastien HERT
     */
    public static void initSO(){
        String login = "jferry";
        String pswd = "obligatoire";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    /**
     * Creates a valid departement of education
     * @author Sébastien HERT
     */
    public static void initDOE(){
        String login = "adde";
        String pswd = "motdepassesécurisé";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    /**
     * Tests for an invalid login
     * @author Sébastien HERT
     */
    public static void initErrorLogin(){
        String login = "toto";
        String pswd = "yapadepano";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    /**
     * Tests for an invalid password
     * @author Sébastien HERT
     */
    public static void initErrorPswd(){
        String login = "dparis";
        String pswd = "yapadepano";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    
}
