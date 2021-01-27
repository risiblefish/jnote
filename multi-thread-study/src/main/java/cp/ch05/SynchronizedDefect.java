package cp.ch05;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class SynchronizedDefect {
    public synchronized void syncMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " started");
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关键字synchronized的缺陷1 ： 无法控制阻塞时间
     * 下面这段代码中，t2何时开始，完全取决于t1何时释放monitor锁
     *
     * @throws InterruptedException
     */
    public static void testDefect1() throws InterruptedException {
        SynchronizedDefect defect = new SynchronizedDefect();
        Thread t1 = new Thread(defect::syncMethod, "T1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(defect::syncMethod, "T2");
        t2.start();
    }


    /**
     * 关键字synchronized的缺陷2 ： 无法中断
     * t2会因为争抢monitor锁而进入blocked状态，虽然在代码中使用interrupt来设置t2的中断标识，但synchronized阻塞不会捕获中断信号，导致t2仍然是阻塞状态
     * @throws InterruptedException
     */
    public static void testDefect2() throws InterruptedException {
        SynchronizedDefect defect = new SynchronizedDefect();
        Thread t1 = new Thread(defect::syncMethod, "T1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(defect::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(2);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());
    }

    public static void main(String[] args) throws InterruptedException {
//       testDefect1();
        testDefect2();
    }
}
