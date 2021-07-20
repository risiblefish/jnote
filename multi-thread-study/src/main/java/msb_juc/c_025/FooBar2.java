package msb_juc.c_025;

/**
 * @author Sean Yu
 * @date 2021/7/16 22:17
 */
class FooBar2 {
    private int n;
    private final Object lock = new Object();
    private volatile boolean isFoo = true;

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock){
                while(!isFoo){
                    lock.wait();
                }
                printFoo.run();
                isFoo = false;
                lock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (lock){
                while(isFoo){
                    lock.wait();
                }
                isFoo = true;
                printBar.run();
                lock.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestFooBar.test(5);
    }
}
