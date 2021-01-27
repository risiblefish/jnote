package interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class TestInterrupted2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //在捕获到interruptedException之后，interrupt标志位会被复位为false
                    System.out.println("now catched interruptedException");
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("thread is interrupted ? " + thread.isInterrupted());
        thread.interrupt();
        //这句话加与不加，结果会大相庭径，思考为什么？
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("thread is interrupted ? " + thread.isInterrupted());
    }
}
