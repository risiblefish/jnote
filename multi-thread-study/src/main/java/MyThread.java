/**
 * @program: multi-thread-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-07 18:42
 **/

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("now sub thread " + i);
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
//        new MyThread().run();
        for (int i = 0; i < 2000; i++) {
            System.out.println("now main thread " + i);
        }
    }
}
