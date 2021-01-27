package cp.ch06;

import java.util.concurrent.TimeUnit;

/**
 * 测试ThreadGroup的interrupt，会将组内所有active的group都打断
 * @author Sean Yu
 */
public class ThreadGroupInterrupt {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("testGroup");
        new Thread(group, () -> {
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(String.format("now %s exit while-loop",Thread.currentThread().getName()));
                    break;
                }
            }
        },"t1").start();

        new Thread(group, () -> {
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(String.format("now %s exit while-loop",Thread.currentThread().getName()));
                    break;
                }
            }
        },"t2").start();

        TimeUnit.MILLISECONDS.sleep(2);
        group.interrupt();
    }
}
