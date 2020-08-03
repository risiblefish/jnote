package status;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 09:53
 **/

public class TestPriority {
    public static void main(String[] args) {
        System.out.println(String.format("主线程信息：name %s %s ",
                Thread.currentThread().getName(), Thread.currentThread().getPriority()));

        PrintPriority printPriority = new PrintPriority();
        Thread t1 = new Thread(printPriority,"t1");
        t1.setPriority(Thread.MIN_PRIORITY);
        Thread t2 = new Thread(printPriority,"t2");
        t2.setPriority(Thread.MAX_PRIORITY);
        Thread t3 = new Thread(printPriority,"t3");
        t3.setPriority(Thread.NORM_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
    }
}

class PrintPriority implements Runnable{
    @Override
    public void run() {
        System.out.println(String.format(" %s %s",
                Thread.currentThread().getName(),Thread.currentThread().getPriority()));
    }
}
