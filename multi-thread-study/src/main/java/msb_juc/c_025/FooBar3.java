package msb_juc.c_025;

import java.util.concurrent.CountDownLatch;

/**
 * @author Sean Yu
 * @date 2021/7/16 22:17
 */
class FooBar3 {
    private int n;

    private volatile boolean isFoo = true;

    public FooBar3(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!isFoo) {
//                //同理
//                Thread.yield();
            }
            printFoo.run();
            isFoo = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (isFoo) {
//                //一定要让出cpu，否则比如执行这个方法的线程启动，就会一直占用CPU，进入死循环
//                Thread.yield();
            }
            printBar.run();
            isFoo = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestFooBar.test(5);
    }
}
