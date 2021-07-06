package msb_juc.c_020;

import msb_juc.util.SleepHelper;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T10_TestReadWriteLock {
    int value;

    public void read(Lock lock) {
        try {
            lock.lock();
            SleepHelper.sleepSeconds(1);
            System.out.println("read over");
        } finally {
            lock.unlock();
        }
    }

    public void write(Lock lock, int newVal) {
        try {
            lock.lock();
            SleepHelper.sleepSeconds(1);
            value = newVal;
            System.out.println("write over");
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用互斥锁
     */
    public static void testMutexLock(){
        T10_TestReadWriteLock test = new T10_TestReadWriteLock();
        final Lock lock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> test.read(lock)).start();
        }

        for (int i = 0; i < 2; i++) {
            final int newVal = i;
            new Thread(() -> test.write(lock, newVal)).start();
        }
    }

    /**
     * 使用读写锁
     */
    public static void testRWLock(){
        T10_TestReadWriteLock test = new T10_TestReadWriteLock();
        final ReadWriteLock rwLock = new ReentrantReadWriteLock();
        Lock readLock = rwLock.readLock();
        Lock writeLock = rwLock.writeLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> test.read(readLock)).start();
        }

        for (int i = 0; i < 2; i++) {
            final int newVal = i;
            new Thread(() -> test.write(writeLock, newVal)).start();
        }
    }

    public static void main(String[] args) {
        /**
         * 起10条读线程，2条写线程，观察使用2种锁时间上的不同
         */
//        testMutexLock();
        testRWLock();
    }
}
