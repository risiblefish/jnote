package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-08-03 10:09
 **/

public class TestThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(()->{
            System.out.println("一个线程执行了");
        });
        service.shutdown();
    }
}
