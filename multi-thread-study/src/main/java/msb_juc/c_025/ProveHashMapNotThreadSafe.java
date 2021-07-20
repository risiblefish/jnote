package msb_juc.c_025;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * 证明hashMap不是线程安全的，而concurrentHashMap是线程安全的
 * @author Sean Yu
 * @date 2021/7/16 7:57
 */
public class ProveHashMapNotThreadSafe {
    static Map<UUID, UUID> map = new HashMap<>();
    static Map<UUID, UUID> cmap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch l = new CountDownLatch(10);
        Runnable r = () -> {
            for (int i = 0; i < 1000; i++) {
                UUID k = UUID.randomUUID();
                UUID v = UUID.randomUUID();
                map.put(k,v);
                cmap.put(k,v);
            }
            l.countDown();
        };

        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }

        l.await();
        System.out.println(map.size());
        System.out.println(cmap.size());
    }
}
