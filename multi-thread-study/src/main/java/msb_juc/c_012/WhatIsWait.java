package msb_juc.c_012;

import msb_juc.util.SleepHelper;

/**
 * 请验证  wait 方法 阻塞的是当前线程
 *
 * @author Sean Yu
 * @date 2021/7/11 2:48
 */
public class WhatIsWait {
    final static Object lock = new Object();
    static int count = 0;
//    static Thread t1;
//    static Thread t2;
    Thread t1;
    Thread t2;

    public static void main(String[] args) {
//        Runnable r = () -> {
//            //确保线程t2启动
//            SleepHelper.sleepSeconds(2);
//            String tName = Thread.currentThread().getName();
//            System.out.println(String.format("now thread[%s] starts", tName));
//            synchronized (lock) {
//                System.out.println(String.format("now %s gets lock", tName));
//                for (; ; ) {
//                    SleepHelper.sleepSeconds(1);
//                    System.out.println(String.format("[%s] now count is %s", tName, count));
//                    count++;
//                    if (count > 5 && "t1".equals(tName)) {
//                        try {
//                            System.out.println("now t2.join()");
//                            t2.join();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        };

        WhatIsWait test = new WhatIsWait();

        test.t1 = new Thread(test::f, "t1");
        test.t2 = new Thread(test::f, "t2");

        test.t1.start();
        //确保t1先启动
        SleepHelper.sleepSeconds(1);
        test.t2.start();
    }

    synchronized void f(){
        //确保线程t2启动
        SleepHelper.sleepSeconds(2);
        String tName = Thread.currentThread().getName();
        System.out.println(String.format("now thread[%s] starts", tName));
        //这里使用同步代码块，会造成死锁
        //原因： 相当于在获取了lock之后，再获取t2，wait只会释放t2，但不会释放lock
        synchronized (lock) {
            System.out.println(String.format("now %s gets lock", tName));
            for (; ; ) {
                SleepHelper.sleepSeconds(1);
                System.out.println(String.format("[%s] now count is %s", tName, count));
                count++;
                if (count > 5 && "t1".equals(tName)) {
                    try {
                        System.out.println("now t2.join()");
                        t2.join();
                        //等价于
//                        synchronized (t2){
//                            while(t2.isAlive()){
//                                //t2.wait() 代表当前线程(t1)让出锁t2，进行等待
//                                t2.wait(0);
//                            }
//                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
