package user;

/**
 * 
 * This class contains all methods and attributes linked to a group
 * 
 * @author Sébastien HERT
 * 
 */

public class Group{

    private String name;
    private int num;

    /**
     * @return the name of the group
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the number of the group
     */
    public int getNum() {
        return this.num;
    }

    /**
     * @param num number of the group to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Constructor
     */
    public Group(){}

    /**
     * Constructor
     * @param s name of the group
     * @param n number of the group
     */
    public Group(String s, int n){
        this.name = s;
        this.num = n;
    }
}