/**
 * @program: java-simple-demos
 * @description:
 * @author: Unuts
 * @create: 2020-06-26 20:50
 **/

public class Person {
    {
        System.out.println("anonymous field");
    }

    static {
        System.out.println("static field");
    }

    public Person() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        try{
            System.out.println("a");
        }catch (Exception e){

        }finally {
            System.out.println("f");
        }
    }
}
