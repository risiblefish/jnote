package msb_juc;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 * @date 2021/7/1 21:57
 */
public class T07_Interrupt_and_sleet {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("the thread is interrupted");
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(5);
        t.interrupt();
    }
}
