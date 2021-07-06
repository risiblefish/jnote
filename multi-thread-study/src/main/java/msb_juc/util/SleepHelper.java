package msb_juc.util;

import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 * @date 2021/7/2 8:03
 */
public class SleepHelper {
    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepMilli(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
