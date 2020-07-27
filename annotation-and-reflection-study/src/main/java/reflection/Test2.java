package reflection;

import java.lang.annotation.ElementType;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-07-26 10:56
 **/


public class Test2 {

    //---------------- 哪些类型可以有class ----------------
    public static void main(String[] args) {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = void.class;
        Class c8 = Class.class;
        Class c9 = char.class;

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);
    }
}
