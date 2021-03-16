package cp.ch16.eatnoodles;

import java.util.concurrent.TimeUnit;

/**
 * 解决吃面问题死锁的方法：
 * 将共享变量封装到同一个变量里去，然后使用单一线程执行的模式，避免交叉锁
 * @author Sean Yu
 */
public class EatNoodleSafeThread extends Thread {
    private final String name;
    private final TablewarePair tablewarePair;

    public EatNoodleSafeThread(String name, TablewarePair tp) {
        this.name = name;
        this.tablewarePair = tp;
    }

    @Override
    public void run() {
        while (true) {
            eat();
        }
    }

    public void eat() {
        synchronized (tablewarePair) {
            System.out.println(String.format("[%s] take up [%s](left)", name, tablewarePair.leftTool));
            System.out.println(String.format("[%s] take up [%s](right)", name, tablewarePair.rightTool));
            System.out.println(String.format("[%s] is eating now", name));
            System.out.println(String.format("[%s] put down [%s](right)", name, tablewarePair.rightTool));
            System.out.println(String.format("[%s] put down [%s](left)", name, tablewarePair.leftTool));
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TablewarePair tp = new TablewarePair(new Tableware("fork"), new Tableware("knife"));
        new EatNoodleSafeThread("MJ",tp).start();
        new EatNoodleSafeThread("Kobe",tp).start();
    }
}

class TablewarePair {
    Tableware leftTool;
    Tableware rightTool;

    public TablewarePair(Tableware leftTool, Tableware rightTool) {
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }
}
