package cp.ch07;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class CaptureThreadException {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(String.format("thread[%s] occur exception : %s", t.getName(), e.getMessage()));
        });

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这里会出现runtime exception
            System.out.println(1/0);
        },"test-thread").start();
    }
}
