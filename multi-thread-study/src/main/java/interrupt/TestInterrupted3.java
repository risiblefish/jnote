package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class TestInterrupted3 {
    public static void main(String[] args){
        System.out.println("main thread is interrupted ? " + Thread.interrupted());

        Thread.currentThread().interrupt();

        System.out.println("main thread is interrupted? " + Thread.currentThread().isInterrupted());

        try {
            System.out.println("now start to sleep");
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("i will be interrupted still");
        }
    }
}
