package consumerProducerProblem;

/**
 * cp-problem sample
 *
 * @author Sean Yu
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tickets t = new Tickets(10);
        Thread pt = new Thread(new Producer(t));
        Thread ct = new Thread(new Consumer(t));
        pt.start();
//        pt.join();
        ct.start();
    }
}

/**
 * 模拟票务系统
 */
class Tickets {
    //允许生产的票的总数
    int size;
    //当前票的数量
    int num = 0;
    //当前是否有票可售
    boolean available = false;

    public Tickets(int size) {
        this.size = size;
    }
}

class Producer implements Runnable {
    Tickets t;

    public Producer(Tickets t) {
        this.t = t;
    }

    @Override
    public void run() {
        System.out.println("准备生产");
        //如果当前生产的票数没有达到容量，就继续生产
        while (t.num < t.size) {
            System.out.println(String.format("生产者生产了第%s张票", ++t.num));
            t.available = true;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("生产结束，此时available为" + t.available);
    }
}

class Consumer implements Runnable {
    Tickets t;
    int i = 0;

    public Consumer(Tickets t) {
        this.t = t;
    }

    @Override
    public void run() {
        System.out.println("准备消费");
        while (i < t.size) {
//            System.out.println(i);
            if (t.available) {
                System.out.println(String.format("消费者消费了第%s张票", ++i));
                if (i == t.num) {
                    t.available = false;
                }
            }
        }
        System.out.println("消费结束");
    }
}
