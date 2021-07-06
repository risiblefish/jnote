/**
 * �����������⣺���Ա�����
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
 * �����������������������������
 * @author mashibing
 */
package msb_juc.c_020_01_Interview_01;

import java.util.ArrayList;
import java.util.List;

//����ȫ
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
			System.out.println("t2 ����");
		}, "t2").start();
	}
}
