package msb_juc.c_000;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 * @date 2021/7/1 23:24
 */
public class T01_WhatIsThread {
    private static class T1 extends Thread {
        public void run(){
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        //new T1().run();
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
