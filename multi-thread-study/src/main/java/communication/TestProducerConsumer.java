package communication;

public class TestProducerConsumer {
    public static void main(String[] args) {
        SyncBuffer buffer = new SyncBuffer();
        new Thread(new Producer(buffer)).start();
        new Thread(new Consumer(buffer)).start();
    }
}

class SyncBuffer {
    Chicken[] buffer = new Chicken[10];
    int count = 0;

    public synchronized void push(Chicken chicken) {
        //如果满了，生产者就等待
        if (count == buffer.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没满，就继续生产
        buffer[count++] = chicken;
        this.notifyAll();
    }

    public synchronized Chicken pop() {
        //如果没有鸡了，消费者就等待
        if (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果还有鸡，则直接消费
        Chicken c = buffer[--count];
        //通知生产者生产
        this.notifyAll();
        return c;
    }

}

class Producer implements Runnable {
    SyncBuffer buffer;

    public Producer(SyncBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            buffer.push(new Chicken());
            System.out.println("生产了第" + i + "只鸡");
        }
    }
}

class Consumer implements Runnable {
    SyncBuffer buffer;

    public Consumer(SyncBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                buffer.pop();
                System.out.println("吃掉了第" + i + "只鸡");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Chicken {
}


