/**
 * @program: java-simple-demos
 * @description:
 * @author: Unuts
 * @create: 2020-06-24 07:29
 **/

public class Demo1 {
    public static void main(String[] args) {
//        //整数扩展： 进制 二进制0b 八进制0 十进制（默认） 十六进制0x
//        int i = 0b10;
//        int i1 = 010;
//        int i2 = 0x10;
//        System.out.println(i);
//        System.out.println(i1);
//        System.out.println(i2);

//        float n1 = 0.1f;
//        double n2 = 1.0/10;
//        double n3 = 0.1d;
//        System.out.println(n1 == n2);
//        System.out.println(n1 == n3);
//        System.out.println(n2 == n3);

        String sa = new String("hello");
        String sb = new String("hello");
        System.out.println(sa == sb);
        String sc = "hello";
        String sd = "hello";
        System.out.println(sc == sd);
        String se = "hel" + "lo";
        System.out.println(sa == se);
    }
}
