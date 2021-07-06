package msb_juc.c_020;

import msb_juc.util.SleepHelper;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sean Yu
 * @date 2021/7/4 17:30
 */
public class T03_ReentrantLock3 {
    Lock lock = new ReentrantLock();

    void m1(){
        try{
            lock.lock();
            for (int i = 0; i < 5; i++) {
                SleepHelper.sleepSeconds(1);
                System.out.print(i + " ");
            }
        }finally {
            lock.unlock();
        }
    }

    void m2(){
        boolean locked = false;
        try{
            locked = lock.tryLock(2, TimeUnit.SECONDS);
            System.out.print("m2 executed... locked?[" + locked + "] ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock3 rl = new T03_ReentrantLock3();
        new Thread(rl::m1).start();
        SleepHelper.sleepSeconds(1);
        new Thread(rl::m2).start();
    }
}
