package buyticket;

/**
 * @program: multi-thread-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-07 23:14
 **/

public class TicketToolRunnable implements Runnable{
    private int ticketNum = 10; //假设初始一共10张票
    @Override
    public void run() {
        while(true) {
            if(ticketNum <= 0) {
                break;
            }
            //模拟抢票
            System.out.println(String.format(" %s 抢到了第 %s 张票",Thread.currentThread().getName(),ticketNum--));
            try {
                Thread.sleep(200); //模拟延时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketToolRunnable ticketToolRunnable = new TicketToolRunnable();
        new Thread(ticketToolRunnable,"小明").start();
        new Thread(ticketToolRunnable,"小王").start();
        new Thread(ticketToolRunnable,"黄牛").start();
    }
}
