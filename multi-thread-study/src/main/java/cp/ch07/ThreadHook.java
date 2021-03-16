package cp.ch07;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class ThreadHook {
    public static void main(String[] args) {

        //定义hook线程的线程体
        Runnable runnable = () -> {
            System.out.println(String.format("thread [%s] is running", Thread.currentThread().getName()));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("thread [%s] will exit", Thread.currentThread().getName()));
        };


        Runtime.getRuntime().addShutdownHook(new Thread(runnable,"t1"));
        Runtime.getRuntime().addShutdownHook(new Thread(runnable,"t2"));

        System.out.println("now this program will stop");
    }
}
