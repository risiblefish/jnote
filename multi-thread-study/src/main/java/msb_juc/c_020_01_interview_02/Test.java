package msb_juc.c_020_01_interview_02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Sean Yu
 * @date 2021/7/5 21:39
 */
public class Test {
    volatile static boolean isNum = true;
    static Thread t1, t2;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        t1 = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < 26; i++) {
                if (!isNum) {
                    lock.unlock();
                } else {
                    System.out.println(i + 1);
                    isNum = false;
                }
            }
        });

        t2 = new Thread(() -> {
            char c = 'A';
            lock.lock();
            for (int i = 0; i < 26; i++) {
                if (isNum) {
                    lock.unlock();
                } else {
                    System.out.println((char) c++);
                    isNum = true;
                }
            }
        });

        t1.start();
        t2.start();
    }
}
