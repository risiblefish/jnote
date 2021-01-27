package cp.ch04;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class EventQueue {
    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            while (eventQueue.size() >= max) {
                try {
                    console("the queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console("the new event is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            while (eventQueue.isEmpty()) {
                try {
                    console("the queue is empty");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            eventQueue.notifyAll();
            console(String.format("the event [%s] is handled", event));
            return event;
        }
    }

    private void console(String message) {
        System.out.println(String.format("%s : %s", Thread.currentThread().getName(), message));
    }

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        //生产者线程1
        new Thread(() -> {
            while(true) {
                eventQueue.offer(new EventQueue.Event());
            }
        }).start();

        //生产者线程2
        new Thread(() -> {
            while(true) {
                eventQueue.offer(new EventQueue.Event());
            }
        }).start();

        //消费者线程1
        new Thread(()->{
            while(true){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //消费者线程2
        new Thread(()->{
            while(true){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
