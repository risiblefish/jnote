package cp.ch05;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @author Sean Yu
 */
public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            int randomInt = ThreadLocalRandom.current().nextInt(5);
            System.out.println(String.format("%s get the lock", Thread.currentThread()));
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.format("%s will release the lock", Thread.currentThread()));
            lock.unlock();
        }
    }

    public void syncMethodTimeoutable() {
        try {
            lock.lock(1000);
            System.out.println(String.format("%s get the lock", Thread.currentThread()));
            int randomInt = ThreadLocalRandom.current().nextInt(5);
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 启用多个线程调用syncMethod，观察同一时间是否只有一个线程获得了锁
     */
    private static void testLock() {
        BooleanLockTest blt = new BooleanLockTest();
        IntStream.range(0, 10).mapToObj(i -> new Thread(blt::syncMethod)).forEach(Thread::start);
    }

    /**
     * 测试线程能被中断
     * @throws InterruptedException
     */
    private static void testInterrupt() throws InterruptedException {
        BooleanLockTest blt = new BooleanLockTest();
        new Thread(blt::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        t2.interrupt();
    }

    /**
     * 测试线程能够设置超时
     * @throws InterruptedException
     */
    private static void testTimeout() throws InterruptedException {
        BooleanLockTest blt = new BooleanLockTest();
        new Thread(blt::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethodTimeoutable, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
    }

    public static void main(String[] args) throws InterruptedException {
//        testLock();
//        testInterrupt();
        testTimeout();
    }
}
