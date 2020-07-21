/**
 * @program: multi-thread-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-07 22:43
 **/

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println("now sub thread " + i);
        }
    }

    public static void main(String[] args) {
        new Thread(new MyRunnable()).start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("now main thread " + i);
        }
    }
}
