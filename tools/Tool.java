package tools;

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

    public static ObservableList fromALtoOL(ArrayList<?> list){
        ObservableList o = new ObservableList();
        return o;
    }

}