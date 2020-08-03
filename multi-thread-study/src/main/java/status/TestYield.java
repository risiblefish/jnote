package status;

import java.util.stream.IntStream;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-07-29 21:41
 **/

public class TestYield {
    private final static String START_FORMAT = "%s now start";
    private final static String STOP_FORMAT = "%s now stop";

    public static void main(String[] args) {
        test();
    }

    static void test(){
        new Thread( () -> {
            System.out.println(String.format(START_FORMAT, Thread.currentThread().getName()));
            Thread.yield();
            System.out.println(String.format(STOP_FORMAT, Thread.currentThread().getName()));
        },"a").start();

        new Thread( () -> {
            System.out.println(String.format(START_FORMAT, Thread.currentThread().getName()));
            Thread.yield();
            System.out.println(String.format(STOP_FORMAT, Thread.currentThread().getName()));
        },"b").start();
    }
}
