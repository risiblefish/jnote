package cp.ch06;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class ThreadGroupBasic {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup g1 = new ThreadGroup("g1");
        Thread thread = new Thread(g1, () -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread");
        thread.setDaemon(true);
        thread.start();

        TimeUnit.MILLISECONDS.sleep(1);

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        System.out.println(String.format("activeCount=%s", mainGroup.activeCount()));
        System.out.println(String.format("activeGroupCount=%s", mainGroup.activeGroupCount()));
        System.out.println(String.format("getMaxPriority=%s", mainGroup.getMaxPriority()));
        System.out.println(String.format("getName=%s", mainGroup.getName()));
        System.out.println(String.format("getParent=%s", mainGroup.getParent()));
        mainGroup.list();
        System.out.println("----------------------");
        //如果parent是自己，也会返回true
        System.out.println(String.format("parent Of g1 ?  : %s", mainGroup.parentOf(g1)));
        System.out.println(String.format("parent Of main ?  : %s", mainGroup.parentOf(mainGroup)));
    }
}
