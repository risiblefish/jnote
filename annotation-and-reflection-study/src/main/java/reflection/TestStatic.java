package reflection;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-07-26 12:13
 **/

public class TestStatic {
    static{
        System.out.println("now m is 300");
        m = 300;
    }

    static int m = 100;

    public TestStatic() {
        System.out.println("now m is 500");
        m = 500;
    }

    public static void main(String[] args) {
        System.out.println(m);
    }
}
