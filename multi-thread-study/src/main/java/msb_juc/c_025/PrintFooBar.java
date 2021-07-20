package msb_juc.c_025;

import msb_juc.util.SleepHelper;

public class PrintFooBar {
    public static void main(String[] args) {
        FooBar fooBar = new FooBar(4);
        Thread printFoo = new Thread(new PrintFoo(fooBar));
        Thread printBar = new Thread(new PrintBar(fooBar));
//        printFoo.start();
        printBar.start();
        SleepHelper.sleepSeconds(1);
        printFoo.start();
    }
}

class FooBar {
    private int n;

    private volatile boolean isFoo = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() {
        for (int i = 0; i < n; i++) {
            while (!isFoo) {
            }
            System.out.print("foo");
            isFoo = false;
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
            while (isFoo) {
            }
            System.out.println("bar");
            isFoo = true;
        }
    }
}

class PrintFoo implements Runnable {

    private FooBar fooBar;

    PrintFoo (FooBar fooBar) {
        this.fooBar = fooBar;
    }

    @Override
    public void run() {
        fooBar.foo();
    }
}

class PrintBar implements Runnable {

    private FooBar fooBar;

    PrintBar (FooBar fooBar) {
        this.fooBar = fooBar;
    }

    @Override
    public void run() {
        fooBar.bar();
    }
}