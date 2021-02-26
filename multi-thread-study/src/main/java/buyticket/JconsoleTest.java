package buyticket;

/**
 * @Author: Sean Yu
 * @Date: 2021/1/11 21:50
 */
public class JconsoleTest {
    public static void main(String[] args) {
        new Thread(new Eat(),"eat-thread").start();
        new Thread(new WatchTV(),"tv-thread").start();
    }


}

class Eat implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("eating");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class WatchTV implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("watch tv");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
