package deadlock;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 23:05
 **/

public class TestDeadLock {
    public static void main(String[] args) {
        DeadLock child1 = new DeadLock(1,"小灰灰");
        DeadLock child2 = new DeadLock(2,"懒羊羊");
        new Thread(child1).start();
        new Thread(child2).start();
    }
}

class DeadLock implements Runnable {

    static String toy1 = "toy1";
    static String toy2 = "toy2";
    int choice;
    String name;

    public DeadLock(int choice, String name) {
        this.name = name;
        this.choice = choice;
    }

    private void exchangeToy() {
        switch (choice) {
            case 1: {
                synchronized (toy1) {
                    System.out.println(name + "拿到了玩具1的锁,等待着对方和它交换玩具");
                    try {
                        Thread.sleep(1000);
                        synchronized (toy2) {
                            System.out.println(name + "交换成功，拿到了玩具2的锁");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case 2: {
                synchronized (toy2) {
                    System.out.println(name + "拿到了玩具2的锁，等待着对方和它交换玩具");
                    try {
                        Thread.sleep(2000);
                        synchronized (toy1) {
                            System.out.println(name + "交换成功，拿到了玩具1的锁");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void run() {
        exchangeToy();
    }
}
