/**
 * �����������⣺���Ա�����
 * ʵ��һ���������ṩ����������add��size
 * д�����̣߳��߳�1���10��Ԫ�ص������У��߳�2ʵ�ּ��Ԫ�صĸ�������������5��ʱ���߳�2������ʾ������
 * 
 * ��lists���volatile֮��t2�ܹ��ӵ�֪ͨ�����ǣ�t2�̵߳���ѭ�����˷�cpu�����������ѭ��������ô���أ�
 * 
 * ����ʹ��wait��notify������wait���ͷ�������notify�����ͷ���
 * ��Ҫע����ǣ��������ַ���������Ҫ��֤t2��ִ�У�Ҳ����������t2�����ſ���
 * 
 * �Ķ�����ĳ��򣬲�����������
 * ���Զ���������������size=5ʱt2�˳�������t1����ʱt2�Ž��յ�֪ͨ���˳�
 * ��������Ϊʲô��
 * 
 * notify֮��t1�����ͷ�����t2�˳���Ҳ����notify��֪ͨt1����ִ��
 * ����ͨ�Ź��̱ȽϷ���
 * @author mashibing
 */
package msb_juc.c_020_01_Interview_01;

import java.util.ArrayList;
import java.util.List;


public class T04_NotifyFreeLock {
	List lists = new ArrayList();
	public void add(Object o) {
		lists.add(o);
	}
	public int size() {
		return lists.size();
	}

	public static void main(String[] args) {
		T04_NotifyFreeLock container = new T04_NotifyFreeLock();
		final Object lock = new Object();
		Thread t1 = new Thread(() -> {
			System.out.println("t1 start");
			for (int i = 0; i < 10; i++) {
				synchronized (lock){
					container.add(new Object());
					System.out.println("add " + i);
					if(container.size() == 5){
						//�Ȼ���t2
						lock.notifyAll();
						try {
							//���ó���
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			System.out.println("t1 end");
		});

		Thread t2 = new Thread(() -> {
			System.out.println("t2 start");
			synchronized (lock){
				while(container.size() != 5){
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("now size is 5, t2 end");
				//����t1����ִ��
				lock.notifyAll();
			}
		});
		t1.start();
		t2.start();
	}
}
