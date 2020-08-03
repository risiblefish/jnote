package status;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 10:59
 **/

public class TestDaemon {
    public static void main(String[] args) {
        Thread yourLife = new Thread(new You());
        Thread godPermanent = new Thread(new God());
        godPermanent.setDaemon(true);
        yourLife.start();
        godPermanent.start();
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("god bless u");
        }
    }
}

class You implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + "年过去了");
        }
        System.out.println("再见世界");
    }
}
