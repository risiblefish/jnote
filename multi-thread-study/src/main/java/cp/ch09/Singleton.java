package cp.ch09;

/**
 * @author Sean Yu
 */
public class Singleton {
    //（1）
    private static Singleton instance = new Singleton();

    private static int x = 0;
    private static int y;
    //(2)
    private Singleton(){
        x++;
        y++;
    }
    public static Singleton getInstance(){
        return instance;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
