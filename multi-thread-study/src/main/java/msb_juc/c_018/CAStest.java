package msb_juc.c_018;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sean Yu
 * @date 2021/7/4 5:57
 */
public class CAStest {
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        count.incrementAndGet();
    }
}
