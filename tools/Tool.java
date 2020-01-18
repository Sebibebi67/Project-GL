package tools;

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
 * 
 */

public class Tool{

    /**
    * Print a String
    * @author Sébastien HERT 
    * @param String s to print
    */
    public static void print(String s){
        System.out.println(s);
    }

    /**
    * Print a Object
    * @author Sébastien HERT 
    * @param Object o to print
    */
    public static void display(Object o){
        System.out.println(o.toString());
    }

    public static List<?> objectToList(Object o){
        List<?> list = new ArrayList<>();
        if (o.getClass().isArray()){
            list = Arrays.asList((Object[]) o);
            
        }else if (o instanceof Collection){
            list = new ArrayList<>((Collection<?>) o);
        }
        return list;
    }

    public static String booleanToString(boolean bool){
        String result = "Non";
        if(bool == true){
            result = "Oui";
        }
        return result;
    }

    public static boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch(NumberFormatException e){}
        return false;
    }

    public static int stringToInt(String str){
        int n = -1;
        try{
            n = Integer.parseInt(str);
            
        }catch(NumberFormatException e){}
        return n;
    }

    public static boolean isHour(int h){
        return (h>=0 && h<24);
    }

    public static boolean isMinute(int m){
        return (m>=0 && m<60);
    }

    public static boolean isTime(String h, String m){
        return Tool.isInt(h) && Tool.isInt(m) && Tool.isHour(Tool.stringToInt(h)) && Tool.isMinute(Tool.stringToInt(m));
    }

    public static Time newTime(String h, String m){
        return Time.valueOf(addingZero(h)+addingZero(m)+"00");
    }

    public static String addingZero(String s){
        if (Tool.stringToInt(s)<10){
            return "0"+s;
        }else{
            return s;
        }
    }

}