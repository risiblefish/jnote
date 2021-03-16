package cp.ch07;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class EmptyExceptionHandler {
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println(mainGroup.getName());
        System.out.println(mainGroup.getParent());
        System.out.println(mainGroup.getParent().getParent());

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //抛出运行时异常
            System.out.println(1/0);
        },"test-thread").start();
    }
}
