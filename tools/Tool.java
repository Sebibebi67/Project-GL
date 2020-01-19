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
    public static void print(Object o){
        System.out.println(o.toString());
    }

    public static void print(ArrayList<String> array){
        for(int i =0; i<array.size(); i++){
            System.out.print(array.get(i));
        }
        System.out.println("");
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

    public static String dateToString(Date date, Time begin, Time end){
        return "Le "+Tool.getFrenchDate(date)+" de "+Tool.getHMTime(begin)+" à "+Tool.getHMTime(end);
    }

    public static String getFrenchDate(Date date){
        String[] frenchDate = date.toString().split("-");
        return frenchDate[2]+"-"+frenchDate[1]+"-"+frenchDate[0];
    }

    public static String getHMTime(Time time){
        String[] newTime = time.toString().split("-");
        return newTime[0]+"H"+newTime[1];
    }

    public static Object[] stringToDate(String s){
        Object[] obj = new Object[3];
        String[] newS = s.split(" ");

        obj[0] = Tool.parseDate(newS[1]);
        obj[1] = Tool.parseTime(newS[3]);
        obj[2] = Tool.parseTime(newS[5]);

        return obj;

    }

    public static Date parseDate(String s){
        String[] newS = s.split("-");
        return  Date.valueOf(newS[2]+"-"+newS[1]+"-"+newS[0]);
    }

    public static Time parseTime(String s){
        String[] newS = s.split("H");
        return Time.valueOf(newS[1]+":"+newS[0]+":00");
    }

}