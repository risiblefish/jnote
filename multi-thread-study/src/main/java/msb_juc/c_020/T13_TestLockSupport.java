package msb_juc.c_020;

import msb_juc.util.SleepHelper;

import java.util.concurrent.locks.LockSupport;

public class T13_TestLockSupport {
    public static void main(String[] args) {
//        testPark();
        testUnparkBeforePark();
    }
    public static void testPark(){
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if( i == 5){
                    //如果不unpark，会一直阻塞
                    LockSupport.park();
                }
                SleepHelper.sleepSeconds(1);
            }
        });
        t.start();
//        //8秒后执行unpark
//        SleepHelper.sleepSeconds(8);
//        LockSupport.unpark(t);
    }

    public static void testUnparkBeforePark(){
        Thread t = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if( i == 5){
                    LockSupport.park();
                }
                SleepHelper.sleepSeconds(1);
            }
        });
        t.start();
        //在park之前执行unpark，会让park失效
        LockSupport.unpark(t);
    }
}
