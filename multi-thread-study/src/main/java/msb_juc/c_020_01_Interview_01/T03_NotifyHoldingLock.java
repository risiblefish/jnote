/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，该怎么做呢？
 * 
 * 这里使用wait和notify做到，wait会释放锁，而notify不会释放锁
 * 需要注意的是，运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * 
 * 阅读下面的程序，并分析输出结果
 * 可以读到输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出
 * 想想这是为什么？
 * @author mashibing
 */
package msb_juc.c_020_01_Interview_01;

import java.util.ArrayList;
import java.util.List;


public class T03_NotifyHoldingLock { //wait notify
	volatile List lists = new ArrayList();

	public void add(Object o) {
		lists.add(o);
	}

	public int size() {
		return lists.size();
	}
	
	public static void main(String[] args) {
		T03_NotifyHoldingLock container = new T03_NotifyHoldingLock();
		final Object lock = new Object();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				synchronized (lock){
					container.add(new Object());
					System.out.println("add : " + (i + 1) );
					if(container.size() == 5){
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			System.out.println("add完毕，t1结束");
		});

		Thread t2 = new Thread(() -> {
			synchronized (lock) {
				while(container.size() != 5){
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("当前size为5， t2结束");
				lock.notify();
			}
		});

		t1.start();
		t2.start();
	}
}
