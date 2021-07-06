package msb_juc.c_000;

import msb_juc.util.SleepHelper;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sean Yu
 * @date 2021/7/2 7:58
 */
public class T04_ThreadState {
    public static void main(String[] args) throws Exception{
        //===================================================
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                SleepHelper.sleepSeconds(1);
                System.out.print(i + " ");
            }
            System.out.println();
        });

        System.out.println("1: " + t1.getState());
        t1.start();
        t1.join();
        //上面join的作用，确保在t1运行完之后，再输出其状态
        System.out.println("3: " + t1.getState());

        //===================================================
        Thread t2 = new Thread(() -> {
            //挂起当前线程（即进入waiting状态）
            LockSupport.park();
            System.out.println("t2 goes on!");
            SleepHelper.sleepSeconds(3);
        });

        t2.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("4: " + t2.getState());
        //唤醒该线程
        LockSupport.unpark(t2);
        SleepHelper.sleepSeconds(1);
        System.out.println("5: " + t2.getState());

        //===================================================
        final Object o = new Object();
        Thread t3 = new Thread(() -> {
            synchronized (o) {
                System.out.println("t3 得到了锁 o");
            }
        });

        new Thread(() -> {
            synchronized (o){
                SleepHelper.sleepSeconds(3);
            }
        }).start();

        SleepHelper.sleepSeconds(1);

        t3.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("6: " + t3.getState());

        //===================================================
        final Lock lock = new ReentrantLock();
        Thread t4 = new Thread(() -> {
            lock.lock();
            System.out.println("t4 得到了锁 lock");
            lock.unlock();
        });

        new Thread(() -> {
            lock.lock();
            SleepHelper.sleepSeconds(3);
            lock.unlock();
        }).start();

        SleepHelper.sleepSeconds(1);

        t4.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("7: " + t4.getState());

        //===================================================
        Thread t5 = new Thread(LockSupport::park);
        t5.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("8: " + t5.getState());
        LockSupport.unpark(t5);
    }
}
