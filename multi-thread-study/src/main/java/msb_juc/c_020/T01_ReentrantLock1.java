package msb_juc.c_020;

import msb_juc.util.SleepHelper;

/**
 * @author Sean Yu
 * @date 2021/7/4 11:09
 */
public class T01_ReentrantLock1 {
    synchronized void m1() {
        for (int i = 0; i < 5; i++) {
            SleepHelper.sleepSeconds(1);
            System.out.print(i+" ");
            if(i == 2) {
                m2();
            }
        }
    }

    synchronized void m2(){
        System.out.print("m2 ... ");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 rl = new T01_ReentrantLock1();
        new Thread(rl::m1).start();
        //让另一个线程在1秒后启动
        SleepHelper.sleepSeconds(1);
        new Thread(rl::m2).start();
    }
}
