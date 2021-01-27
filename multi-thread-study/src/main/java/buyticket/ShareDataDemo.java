package buyticket;

/**
 * 同一个线程体的数据在多个线程中会被共享
 * @author Sean Yu
 */
public class ShareDataDemo {
    public static void main(String[] args) {
        TicketSystem ts = new TicketSystem();
        Thread t1 = new Thread(ts, "窗口1");
        Thread t2 = new Thread(ts, "窗口2");
        Thread t3 = new Thread(ts, "窗口3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketSystem implements Runnable {
    int count = 20;

    @Override
    public void run() {
        while (count != 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了第" + count-- + "张票");
        }
    }
}
