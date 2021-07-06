package msb_juc;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 * @date 2021/7/1 22:10
 */
public class T09_Interrupt_and_sync {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            synchronized (o) {
                System.out.println("now get o-lock");
            }
            System.out.println("t2 end");
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t2.interrupt();
    }
}
