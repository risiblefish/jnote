package cp.ch17;

import static java.lang.Thread.currentThread;

/**
 * @author Sean Yu
 */
public class ReadWriteLockTest {
    private final static String text = "thisistheexampleforreadwritelock";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(50);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int index = 0; index < text.length(); index++) {
                    try {
                        char c = text.charAt(index);
                        shareData.write(c);
                        System.out.println(String.format("current thread[%s] writes [%s]", currentThread(), c));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(String.format("current thread[%s] reads [%s]", currentThread(), new String(shareData.read())));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
