package cp.ch06;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class ThreadGroupDestroy {
    public static void main(String[] args) throws InterruptedException {
//        testDestroySuccess();
        testDestroyFail();
    }

    private static void testDestroySuccess(){
        ThreadGroup group = new ThreadGroup("testGroup");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(String.format("group.isDestroyed = [%s]", group.isDestroyed()));
        mainGroup.list();
        group.destroy();
        System.out.println(String.format("group.isDestroyed = [%s]", group.isDestroyed()));
        mainGroup.list();
    }

    private static void testDestroyFail() throws InterruptedException {
        ThreadGroup group = new ThreadGroup("testGroup");
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        new Thread(group, ()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"running").start();

        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println(String.format("group.isDestroyed = [%s]", group.isDestroyed()));
        mainGroup.list();
        group.destroy();
        System.out.println(String.format("group.isDestroyed = [%s]", group.isDestroyed()));
        mainGroup.list();
    }
}
