/**
 * 曾经的面试题：（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 
 * 分析下面这个程序，能完成这个功能吗？
 * @author mashibing
 */
package msb_juc.c_020_01_Interview_01;

import java.util.ArrayList;
import java.util.List;

//不安全
public class T01_WithoutVolatile {
	List list = new ArrayList();

	public void add(Object o) {
		list.add(o);
	}

	public int size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		T01_WithoutVolatile c = new T01_WithoutVolatile();

		new Thread(() -> {
			for(int i=0; i<10; i++) {
				c.add(new Object());
				System.out.println("add " + i);
			}
		}, "t1").start();
		
		new Thread(() -> {
			while(true) {
				if(c.size() == 5) {
					break;
				}
			}
			System.out.println("t2 结束");
		}, "t2").start();
	}
}
