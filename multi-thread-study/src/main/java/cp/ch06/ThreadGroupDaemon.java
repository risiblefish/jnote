package cp.ch06;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class ThreadGroupDaemon {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup g1 = new ThreadGroup("g1");
        new Thread(g1, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "g1-t1").start();

        ThreadGroup g2 = new ThreadGroup("g2");
        new Thread(g2, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "g2-t2").start();

        //daemon group会在没有active线程的时候自动destroy
        g2.setDaemon(true);
        TimeUnit.SECONDS.sleep(3);
        System.out.println(g1.isDestroyed());
        System.out.println(g2.isDestroyed());
    }
}
