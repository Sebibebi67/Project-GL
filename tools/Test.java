package tools;

/**
 * 
 * This class is composed by every test made in the conception
 * @author Sébastien HERT 
 * 
 */
public class Test{


    public static void initStudent(){
        String login = "dparis";
        String pswd = "priority";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());

    }

    public static void initProfessor(){
        String login = "leuler";
        String pswd = "eipimoins1egal0";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    public static void initSO(){
        String login = "jferry";
        String pswd = "obligatoire";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    public static void initDOE(){
        String login = "adde";
        String pswd = "motdepassesécurisé";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    public static void initErrorLogin(){
        String login = "toto";
        String pswd = "yapadepano";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    public static void initErrorPswd(){
        String login = "dparis";
        String pswd = "yapadepano";
        Main.createPerson(pswd, login);
        Tool.print(Stockage.getPerson());
    }

    
}
