package msb_juc.c_025;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author Sean Yu
 * @date 2021/7/17 19:28
 */
class FooBar4 {
    private int n;
    TransferQueue<String> q = new LinkedTransferQueue<>();

    public FooBar4(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
          printFoo.run();
          q.transfer("b");
          q.take();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            q.take();
            printBar.run();
            q.transfer("f");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestFooBar.test(5);
    }
}