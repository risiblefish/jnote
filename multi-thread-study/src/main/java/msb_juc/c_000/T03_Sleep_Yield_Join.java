package msb_juc.c_000;

import java.util.concurrent.TimeUnit;

/**
 * 从3个方法打印的顺序来理解
 *
 * @author Sean Yu
 * @date 2021/7/2 7:24
 */
public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }

    static void testSleep(){
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("A " + i);
                    TimeUnit.MICROSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    static void testYield(){
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("A " + i);
                if(i % 10 == 0){
                    Thread.yield();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("B==============" + i);
                if(i % 10 == 0){
                    Thread.yield();
                }
            }
        }).start();
    }

    static void testJoin(){
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A " + i);
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("B " + i);
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
