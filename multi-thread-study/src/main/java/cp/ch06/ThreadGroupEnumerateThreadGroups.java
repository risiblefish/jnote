package cp.ch06;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class ThreadGroupEnumerateThreadGroups {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup myG1 = new ThreadGroup("myG1");
        ThreadGroup myG2 = new ThreadGroup(myG1, "myG2");

        TimeUnit.MILLISECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup[] list = new ThreadGroup[mainGroup.activeCount()];

        int recurseSize = mainGroup.enumerate(list);
        System.out.println(recurseSize);

        recurseSize = mainGroup.enumerate(list, false);
        System.out.println(recurseSize);
    }
}
