package msb_juc.c_025;

import msb_juc.util.SleepHelper;

/**
 * @author Sean Yu
 * @date 2021/7/16 22:26
 */
public class TestFooBar {

    public static void test(int n) throws InterruptedException {

        FooBar3 fb = new FooBar3(n);

        Runnable printFoo = () -> {
            System.out.println("foo");
        };

        Runnable printBar = () -> {
            System.out.println("bar");
        };

        //故意让bar先启动
        new Thread(() -> {
            try {
                fb.bar(printBar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        SleepHelper.sleepSeconds(2);

        new Thread(() -> {
            try {
                fb.foo(printFoo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
