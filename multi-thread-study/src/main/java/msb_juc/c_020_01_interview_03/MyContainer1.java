/**
 * �����⣺дһ���̶�����ͬ��������ӵ��put��get�������Լ�getCount������
 * �ܹ�֧��2���������߳��Լ�10���������̵߳���������
 * <p>
 * ʹ��wait��notify/notifyAll��ʵ��
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
        this.notifyAll();//֪ͨ�������߳̽�������
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
        this.notifyAll(); //֪ͨ�������߳̽�������
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<Object> container = new MyContainer1<>();
        //�����������߳�
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            }, "c" + i).start();
        }

        //�����������߳�
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }

    }
}
