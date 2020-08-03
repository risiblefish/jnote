package sync;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 11:59
 **/

public class SafeBuyingTickets {
    public static void main(String[] args) {
        SafeStation station = new SafeStation();
        Thread t1 = new Thread(station, "苦逼的我");
        Thread t2 = new Thread(station, "可恶的黄牛");
        Thread t3 = new Thread(station, "路人");
        Thread t4 = new Thread(station, "帮忙抢票的朋友");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

class SafeStation implements Runnable{
    int num = 10;
    boolean flag = true;

    @Override
    public synchronized void run() {
        while(flag) {
            if(num <= 0) {
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s买到了第%s张票",Thread.currentThread().getName(),num--));
        }
    }
}
