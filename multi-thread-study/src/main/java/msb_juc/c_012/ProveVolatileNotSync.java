package msb_juc.c_012;

/**
 * ��֤�� volatile ����ͬ����
 *
 * @author Sean Yu
 * @date 2021/7/9 12:27
 */
public class ProveVolatileNotSync {
    volatile int num = 0;
    void f() {
        System.out.println(Thread.currentThread().getName() + " started");
        for (int j = 0; j < 100000; j++) {
//            System.out.println(Thread.currentThread().getName() + " ִ��" + num + "��1�Ĳ���" );
            num++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ProveVolatileNotSync test = new ProveVolatileNotSync();
        Thread t1 = new Thread(test::f, "t1");
        Thread t2 = new Thread(test::f, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(test.num);
    }
}
