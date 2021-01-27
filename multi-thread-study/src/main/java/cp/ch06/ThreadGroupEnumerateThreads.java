package cp.ch06;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class ThreadGroupEnumerateThreads {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup myGroup = new ThreadGroup("myGroup");
        Thread myThread = new Thread(myGroup, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "myThread");

        Thread myThread2 = new Thread(myGroup, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "myThread2");

        myThread.start();
        myThread2.start();

        TimeUnit.SECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeCount()];

        int recurseSize = mainGroup.enumerate(list, false);
        System.out.println(recurseSize);
        System.out.println("-----------non recurse-------------");
        Arrays.stream(list).forEach(t -> {
            if(t != null){
                System.out.println(t.getName());
            }
        });

        recurseSize = mainGroup.enumerate(list);
        System.out.println("-----------recurse-------------");
        System.out.println(recurseSize);
        Arrays.stream(list).forEach(t -> {
            System.out.println(t.getName());
        });



    }
}
