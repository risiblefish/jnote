package msb_juc.c_020_01_interview_02;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Sean Yu
 * @date 2021/7/5 21:23
 */
public class T01_LockSupport {
    static Thread t1,t2;
    public static void main(String[] args) {
        t1 = new Thread(() -> {
            //确保t2先启动，所以t1开局自行阻塞
            LockSupport.park();
            char c = 'A';
            for (int i = 0; i < 25; i++) {
                System.out.print((char)c++);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
            //只循环25次，第26次打印后不park，并且唤醒t2
            System.out.println(c);
            LockSupport.unpark(t2);
        });

        t2 = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print(i+1);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });

        t1.start();
        t2.start();
    }
}
