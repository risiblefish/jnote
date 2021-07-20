package msb_juc.c_023_02_FromHashtableToCHM;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请编写一个程序，测试hashtable, synchronizedHashMap, concurrentHashMap的读写性能
 *
 * @author Sean Yu
 * @date 2021/7/14 6:18
 */
public class TestPerformance {
    Hashtable<UUID, UUID> ht = new Hashtable<>();
    Map<UUID, UUID> shm = Collections.synchronizedMap(new HashMap<>());
    Map<UUID, UUID> chm = new ConcurrentHashMap<>();

    int dataCount, threadCount;
    UUID[] keys, vals;

    public TestPerformance(int dataCount, int threadCount) {
        this.threadCount = threadCount;
        this.dataCount = dataCount;
        keys = new UUID[dataCount];
        vals = new UUID[dataCount];
        for (int i = 0; i < dataCount; i++) {
            keys[i] = UUID.randomUUID();
            vals[i] = UUID.randomUUID();
        }
    }

    public void htPerformance() throws InterruptedException {
        int seg = dataCount / threadCount;
        long wstart = System.currentTimeMillis();
        //启动threadCount个线程
        for (int i = 0; i < threadCount; i++) {
            final int start = i * seg;
            final int end = start + seg;
            //每个线程执行dateCount / threadCount 次写入
            Thread t = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    ht.put(keys[j], vals[j]);
                }
            });
            t.start();
            t.join();
        }
        long wend = System.currentTimeMillis();
        System.out.println(String.format("HashTable wrote [%s] data in [%s] msec", dataCount, wend - wstart));

        long rstart = System.currentTimeMillis();
        //启动threadCount个线程
        for (int i = 0; i < threadCount; i++) {
            //每个线程执行dataCount次get
            Thread t = new Thread(() -> {
                for (int j = 0; j < dataCount; j++) {
                    ht.get(keys[10]);
                }
            });
            t.start();
            t.join();
        }
        long rend = System.currentTimeMillis();
        System.out.println(String.format("HashTable read : [%s] msec", rend - rstart));
    }

    public void shmPerformance() throws InterruptedException {
        int seg = dataCount / threadCount;
        long wstart = System.currentTimeMillis();
        //启动threadCount个线程
        for (int i = 0; i < threadCount; i++) {
            final int start = i * seg;
            final int end = start + seg;
            //每个线程执行dateCount / threadCount 次写入
            Thread t = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    shm.put(keys[j], vals[j]);
                }
            });
            t.start();
            t.join();
        }
        long wend = System.currentTimeMillis();
        System.out.println(String.format("SynchronizedHashMap wrote : [%s] data in [%s] msec", dataCount, wend - wstart));

        long rstart = System.currentTimeMillis();
        //启动threadCount个线程
        for (int i = 0; i < threadCount; i++) {
            //每个线程执行dataCount次get
            Thread t = new Thread(() -> {
                for (int j = 0; j < dataCount; j++) {
                    shm.get(keys[10]);
                }
            });
            t.start();
            t.join();
        }
        long rend = System.currentTimeMillis();
        System.out.println(String.format("SynchronizedHashMap read : [%s] msec", rend - rstart));
    }

    public void chmPerformance() throws InterruptedException {
        int seg = dataCount / threadCount;
        long wstart = System.currentTimeMillis();
        //启动threadCount个线程
        for (int i = 0; i < threadCount; i++) {
            final int start = i * seg;
            final int end = start + seg;
            //每个线程执行dateCount / threadCount 次写入
            Thread t = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    chm.put(keys[j], vals[j]);
                }
            });
            t.start();
            t.join();
        }
        long wend = System.currentTimeMillis();
        System.out.println(String.format("ConcurrentHashMap wrote : [%s] data in [%s] msec", dataCount, wend - wstart));

        long rstart = System.currentTimeMillis();
        //启动threadCount个线程
        for (int i = 0; i < threadCount; i++) {
            //每个线程执行dataCount次get
            Thread t = new Thread(() -> {
                for (int j = 0; j < dataCount; j++) {
                    chm.get(keys[10]);
                }
            });
            t.start();
            t.join();
        }
        long rend = System.currentTimeMillis();
        System.out.println(String.format("ConcurrentHashMap read : [%s] msec", rend - rstart));
    }

    public static void main(String[] args) throws InterruptedException {
        TestPerformance test = new TestPerformance(10_000_000, 100);
        test.htPerformance();
        test.shmPerformance();
        test.chmPerformance();
    }
}
