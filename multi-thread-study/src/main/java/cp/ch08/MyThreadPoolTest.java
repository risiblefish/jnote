package cp.ch08;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class MyThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        final MyThreadPool threadPool = new BasicThreadPool2(2, 6, 4, 1000);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        while (true) {
            System.out.println("getActiveCount : " + threadPool.getActiveCount());
            System.out.println("getQueueSize : " + threadPool.getQueueSize());
            System.out.println("getCoreSize : " + threadPool.getCoreSize());
            System.out.println("getMaxSize : " + threadPool.getMaxSize());
            System.out.println("======================================");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
