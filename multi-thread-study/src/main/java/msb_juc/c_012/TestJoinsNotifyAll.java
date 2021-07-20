package msb_juc.c_012;

import msb_juc.util.SleepHelper;

/**
 * @author Sean Yu
 * @date 2021/7/11 4:30
 */
public class TestJoinsNotifyAll {
    static Thread t1;
    static Thread t2;
    static Thread t3;
    static int count = 0;
    final static Object lock12 = new Object();
    final static Object lock34 = new Object();

    public static void main(String[] args) {
        Runnable r = () -> {
            //确保每个线程都能启动
            SleepHelper.sleepSeconds(3);
            String tName = Thread.currentThread().getName();
            System.out.println(String.format("now thread[%s] starts", tName));
            for(;;){
                SleepHelper.sleepSeconds(1);
                System.out.println(String.format("[%s] now count is %s", tName, count));
                count++;
                if(count > 5 && "t1".equals(tName)){
                    try {
                        System.out.println("now t2.join()");
                        t2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        t1 = new Thread(r,"t1");
        t2 = new Thread(r,"t2");
//        t3 = new Thread(() -> {
//            System.out.println("now t3 starts and waits");
//            synchronized (lock12){
//                try {
//                    lock34.wait();
//                    System.out.println("t3 ended");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },"t3");

        t1.start();
        SleepHelper.sleepSeconds(1);
        t2.start();
        SleepHelper.sleepSeconds(1);
//        t3.start();
    }
}
