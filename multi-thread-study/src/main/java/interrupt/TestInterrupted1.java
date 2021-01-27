package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class TestInterrupted1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while(true){

            }
        });

        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("thread is interrupted ? " + thread.isInterrupted());
        thread.interrupt();
        System.out.println("thread is interrupted ? " + thread.isInterrupted());
    }
}
