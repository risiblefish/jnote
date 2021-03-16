package cp.ch16.eatnoodles;

/**
 * 模拟死锁
 * @author Sean Yu
 */
public class EatNoodleDeadLockableThread extends Thread {
    private final String name;

    private final Tableware leftTool;
    private final Tableware rightTool;

    public EatNoodleDeadLockableThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    /**
     * 模拟吃东西，先拿起左手餐具，再拿右手餐具，都拿到后，开始进食，然后先放下右手餐具，再放下左手餐具
     */
    private void eat() {
        synchronized (leftTool) {
            System.out.println(String.format("[%s] take up [%s](left)", name, leftTool));
            synchronized (rightTool) {
                System.out.println(String.format("[%s] take up [%s](right)", name, rightTool));
                System.out.println(String.format("[%s] is eating now", name));
                System.out.println(String.format("[%s] put down [%s](right)", name, rightTool));
            }
            System.out.println(String.format("[%s] put down [%s](left)", name, leftTool));
        }
    }


    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNoodleDeadLockableThread("MJ", fork, knife).start();
        new EatNoodleDeadLockableThread("Kobe", knife, fork).start();
    }
}
