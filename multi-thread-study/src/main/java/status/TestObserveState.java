package status;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-02 09:37
 **/

public class TestObserveState {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println("running " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(t.getState());

        t.start();

        System.out.println(t.getState());

        while(t.getState()!= Thread.State.TERMINATED) {
            System.out.println(t.getState());
        }

        System.out.println(t.getState());
    }
}
