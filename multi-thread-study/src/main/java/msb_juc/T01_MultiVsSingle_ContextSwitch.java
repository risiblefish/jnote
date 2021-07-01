package msb_juc;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author Sean Yu
 * @date 2021/7/1 6:58
 */
public class T01_MultiVsSingle_ContextSwitch {
    private static double[] nums = new double[100_000_000];
    private static Random r = new Random();
    private static DecimalFormat df = new DecimalFormat("0.00");

    static {
//        IntStream.range(0,nums.length).parallel().forEach(i -> nums[i] = r.nextDouble());
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        singleThread();
        twoThreads();
        tooManyThreads();
    }

    private static void singleThread() {
        long start = System.currentTimeMillis();
        double result = 0.0;
        for (int i = 0; i < nums.length; i++) {
            result += nums[i];
        }
        long end = System.currentTimeMillis();
        System.out.println("single Thread : " + (end - start) + " result = " + df.format(result));
    }

    private static double res1 = 0.0, res2 = 0.0, res = 0.0;

    private static void twoThreads() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < nums.length / 2; i++) {
                res1 += nums[i];
            }
        });


        Thread t2 = new Thread(() -> {
            for (int i = nums.length / 2; i < nums.length; i++) {
                res2 += nums[i];
            }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        res = res1 + res2;
        long end = System.currentTimeMillis();
        System.out.println("2 Threads : " + (end - start) + " result = " + df.format(res));
    }

    private static void tooManyThreads() throws InterruptedException {
        final int threadCount = 10000;
        Thread[] threads = new Thread[threadCount];
        double[] res = new double[threadCount];
        final int segmentCount = nums.length / threadCount;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int m = i;
            threads[i] = new Thread(() -> {
                for (int j = m * segmentCount; j < (m + 1) * segmentCount && j < nums.length; j++) {
                    res[m] += nums[j];
                }
            });
            latch.countDown();
        }

        double sum = 0.0;
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }

        latch.await();
        for (int i = 0; i < res.length; i++) {
            sum += res[i];
        }

        long end = System.currentTimeMillis();
        System.out.println("10000 Threads : " + (end - start) + " result = " + df.format(sum));
    }
}
