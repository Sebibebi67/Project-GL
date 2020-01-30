package tools;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 
 * This class is made of useful tools to simplify the code
 * 
 * @author Sébastien HERT
 * @author Adam RIVIERE
 * 
 */

public class Tool{

    /**
    * Prints a String
    * @author Sébastien HERT 
    * @param s to print
    */
    public static void print(String s){
        System.out.println(s);
    }

    /**
    * Prints a Object
    * @author Sébastien HERT 
    * @param o to print
    */
    public static void print(Object o){
        System.out.println(o.toString());
    }

    /**
    * Prints an ArrayList
    * @author Sébastien HERT 
    * @param array to print
    */
    public static void print(ArrayList<String> array){
        for(int i =0; i<array.size(); i++){
            System.out.print(array.get(i));
        }
        System.out.println("");
    }

    /**
    * Converts an object into a list
    * @author Sébastien HERT 
    * @param o to convert
    * @return a list made with the object
    */
    public static List<?> objectToList(Object o){
        List<?> list = new ArrayList<>();
        if (o.getClass().isArray()){
            list = Arrays.asList((Object[]) o);
            
        }else if (o instanceof Collection){
            list = new ArrayList<>((Collection<?>) o);
        }
        return list;
    }

    /**
    * Converts a boolean to a string
    * @author Adam RIVIERE
    * @param bool to convert
    * @return string with the value in french
    */
    public static String booleanToString(boolean bool){
        String result = "Non";
        if(bool == true){
            result = "Oui";
        }
        return result;
    }

    /**
    * Verifies if a string is an integer
    * @author Sébastien HERT 
    * @param str to verify
    * @return true or false
    */
    public static boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){}
        return false;
    }

    /**
    * Converts a string to integer
    * @author Sébastien HERT 
    * @param str to convert
    * @return an integer
    */
    public static int stringToInt(String str){
        int n = -1;
        try{
            n = Integer.parseInt(str);
            
        }catch(NumberFormatException e){}
        return n;
    }

    /**
    * Verifies if an int is an hour
    * @author Sébastien HERT 
    * @param h to verify
    * @return true or false
    */
    public static boolean isHour(int h){
        return (h>=0 && h<24);
    }

    /**
    * Verifies if an int is a minute
    * @author Sébastien HERT 
    * @param m to verify
    * @return true or false
    */
    public static boolean isMinute(int m){
        return (m>=0 && m<60);
    }

    /**
    * Verifies if a couple of integers is a time
    * @author Sébastien HERT 
    * @param h to verify
    * @param m to verify
    * @return true or false
    */
    public static boolean isTime(String h, String m){
        return Tool.isInt(h) && Tool.isInt(m) && Tool.isHour(Tool.stringToInt(h)) && Tool.isMinute(Tool.stringToInt(m));
    }

    /**
    * Converts a couple of integers to a time
    * @author Sébastien HERT 
    * @param h to use
    * @param m to use
    * @return a time
    */
    public static Time newTime(String h, String m){
        return Time.valueOf(addingZero(h)+addingZero(m)+"00");
    }

    /**
    * Adds a zero before a string
    * @author Sébastien HERT 
    * @param s to modify
    * @return the modified string
    */
    public static String addingZero(String s){
        if (Tool.stringToInt(s)<10){
            return "0"+s;
        }else{
            return s;
        }
    }

    /**
    * Converts a date and time to a string
    * @author Sébastien HERT 
    * @param date to convert
    * @param begin to convert
    * @param end to convert
    * @return a string with the date and time
    */
    public static String dateToString(Date date, Time begin, Time end){
        return "Le "+Tool.getFrenchDate(date)+" de "+Tool.getHMTime(begin)+" à "+Tool.getHMTime(end);
    }

    /**
    * Converts a Date to a string
    * @author Sébastien HERT 
    * @param date to convert
    * @return a string with the date
    */
    public static String getFrenchDate(Date date){
        String[] frenchDate = date.toString().split("-");
        return frenchDate[2]+"-"+frenchDate[1]+"-"+frenchDate[0];
    }

    /**
    * Converts a time to a string
    * @author Sébastien HERT 
    * @param time to convert
    * @return a string with the time
    */
    public static String getHMTime(Time time){
        // System.out.println(time.toString());
        String[] newTime = time.toString().split(":");
        return newTime[0]+"H"+newTime[1];
    }    

    /**
    * Converts a string to an object with three parts
    * @author Sébastien HERT 
    * @param s to convert
    * @return a three parts object
    */
    public static Object[] stringToDate(String s){
        Object[] obj = new Object[3];
        String[] newS = s.split(" ");

        obj[0] = Tool.parseDate(newS[1]);
        obj[1] = Tool.parseTime(newS[3]);
        obj[2] = Tool.parseTime(newS[5]);

        return obj;

    }

    /**
    * Converts a string to a date
    * @author Sébastien HERT 
    * @param s to convert
    * @return a date
    * @see Tools.dateToString
    */
    public static Date parseDate(String s){
        String[] newS = s.split("-");
        return  Date.valueOf(newS[2]+"-"+newS[1]+"-"+newS[0]);
    }

    /**
    * Converts a string to a time
    * @author Sébastien HERT 
    * @param s to convert
    * @return a time
    * @see Tools.timeToString
    */
    public static Time parseTime(String s){
        String[] newS = s.split("H");
        return Time.valueOf(newS[1]+":"+newS[0]+":00");
    }

    public static String stringForStudent(String name, String firstName, String login){
        return name+" "+firstName+" - "+login;
    }

    public static String getLogin(String student){
        //to remove a nullPointerException
        if(student != null) {
            String[] s = student.split("- ");
            return s[1];
        }
        else return "";
    }

}