package msb_juc.c_020;

import java.util.concurrent.Exchanger;

public class T12_TestExchanger {
    static Exchanger<String> ex = new Exchanger();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                //exchange 在成功之前会阻塞
                s = ex.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = ex.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }
}
