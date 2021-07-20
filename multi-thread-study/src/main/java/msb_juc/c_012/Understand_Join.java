package msb_juc.c_012;

import msb_juc.util.SleepHelper;

/**
 * 验证： t.join 只是让调用这个方法的线程进入等待队列
 *
 * @author Sean Yu
 * @date 2021/7/9 17:31
 */
public class Understand_Join {
    volatile int num = 0;

    void f() {
        String tName = Thread.currentThread().getName();
        System.out.println(String.format("%s started", tName));
        for (int j = 0; j < 5; j++) {
            SleepHelper.sleepSeconds(1);
            System.out.println(String.format("now [%s] 执行 %s 加1的操作", tName, j));
            num++;
        }
        System.out.println(String.format("now [%s] ended", tName));
    }

    public static void main(String[] args) throws InterruptedException {
        Understand_Join test = new Understand_Join();
        Thread t1 = new Thread(test::f, "t1");
        t1.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("now t1. join");
        t1.join();
        System.out.println("now back to main");
        System.out.println("now main ended");
    }
}
