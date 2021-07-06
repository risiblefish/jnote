/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 * <p>
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 *
 * @author mashibing
 */
package msb_juc.c_020_01_interview_03;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {
    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

//	private Lock lock = new ReentrantLock();
//	private Condition producer = lock.newCondition();
//	private Condition consumer = lock.newCondition();

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        lock.lock();
        while (count == MAX) {
            try {
                producer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        ++count;
        consumer.signalAll();
        lock.unlock();
    }

    public T get() {
        T t = null;
        lock.lock();
        while (count == 0) {
            try {
                consumer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count--;
        producer.signalAll();
        lock.unlock();
        return t;
    }

    public static void main(String[] args) {
        ThreadLocal<Object> tl = new ThreadLocal<>();
        tl.remove();
        MyContainer2<String> c = new MyContainer2<>();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}
