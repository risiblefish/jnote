/**
 * һ��ͬ���������Ե�������һ��ͬ��������һ���߳��Ѿ�ӵ��ĳ������������ٴ������ʱ����Ȼ��õ��ö������.
 * Ҳ����˵synchronized��õ����ǿ������
 * �����Ǽ̳����п��ܷ��������Σ�������ø����ͬ������
 * @author mashibing
 */
package msb_juc.c_010;

import msb_juc.util.SleepHelper;

public class T {
	synchronized void m() {
		System.out.println("m start");
		SleepHelper.sleepSeconds(1);
		System.out.println("m end");
	}
	public static void main(String[] args) {
		new TT().m();
	}
}

class TT extends T {
	@Override
	synchronized void m() {
		System.out.println("child m start");
		super.m();
		System.out.println("child m end");
	}
}