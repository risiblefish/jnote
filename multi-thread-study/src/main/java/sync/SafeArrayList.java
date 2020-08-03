package sync;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 17:35
 **/

public class SafeArrayList {
    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        try {
            lock.lock();
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> {
                    list.add(Thread.currentThread().getName());
                }).start();
            }
            System.out.println(list.size());
        } finally {
            lock.unlock();
        }
    }
}
