package user;

public class Group{

    private String name;
    private int num;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Group(){}

    public Group(String s, int n){
        this.name = s;
        this.num = n;
    }
}