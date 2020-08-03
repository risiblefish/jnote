package status;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 09:08
 **/
//
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread vip = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(String.format("vip #%s 插队来了", i));
            }
        });

        vip.start();

        for (int i = 0; i < 100; i++) {
            if (i == 10) {
                vip.join();
            }
            System.out.println("main #" + i);
        }
    }
}




