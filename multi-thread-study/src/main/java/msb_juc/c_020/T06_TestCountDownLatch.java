package msb_juc.c_020;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T06_TestCountDownLatch {
    volatile int sum = 0;
    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        T06_TestCountDownLatch test = new T06_TestCountDownLatch();
        test.usingCDL(10);
    }

    public void usingCDL(int n){
        CountDownLatch latch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            new Thread(()->{
                try{
                    lock.lock();
                    System.out.println(String.format("Thread: %s , sum: %s",Thread.currentThread().getName(),sum));
                    sum++;
                }finally {
                    lock.unlock();
                }
                latch.countDown();
            }).start();
        }
        try{
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0; j<10000; j++) result += j;
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0; j<10000; j++) result += j;
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");
    }
}
