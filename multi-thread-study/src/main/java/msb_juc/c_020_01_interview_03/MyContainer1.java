/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 *
 * @author mashibing
 */
package msb_juc.c_020_01_interview_03;

import java.util.LinkedList;

public class MyContainer1<T> {
    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (count == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        ++count;
        this.notifyAll();//通知消费者线程进行消费
    }

    public synchronized T get() {
        T t = null;
        while (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count--;
        this.notifyAll(); //通知生产者线程进行生产
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<Object> container = new MyContainer1<>();
        //启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            }, "c" + i).start();
        }

        //启动生产者线程
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }

    }
}
