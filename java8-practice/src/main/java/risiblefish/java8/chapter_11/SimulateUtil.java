package risiblefish.java8.chapter_11;

import risiblefish.java8.chapter_11.sub_11_2.Shop;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: java8
 * @description: 模拟各种操作
 * @author: Unuts
 * @create: 2020-03-24 14:49
 **/

public class SimulateUtil {

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用并行流生成n个商店
     *
     * @param n
     * @return
     */
    public static Supplier<List<Shop>> shopSupplierParallel(int n) {
        return () -> {
            return IntStream.rangeClosed(1, n).boxed()
                    .parallel()
                    .map(i -> generateRandomShop())
                    .collect(Collectors.toList());
        };
    }


    /**
     * 用并行流生成n个商店
     *
     * @param n
     * @return
     */
    public static Supplier<List<risiblefish.java8.chapter_11.sub_11_4.Shop>> discountShopSupplierParallel(int n) {
        return () -> {
            return IntStream.rangeClosed(1, n).boxed()
                    .parallel()
                    .map(i -> generateRandomDiscountShop())
                    .collect(Collectors.toList());
        };
    }

    /**
     * 顺序生成n个商店
     *
     * @param n
     * @return
     */
    public static Supplier<List<Shop>> shopSupplierAsync(int n) {
        return () -> {
            return IntStream.rangeClosed(1, n).boxed()
                    .map(i -> generateRandomShop())
                    .collect(Collectors.toList());
        };
    }


    /**
     * 用Future异步生成n个商店,并定制executor
     *
     * @param n
     * @return
     */
    public static Supplier<List<Shop>> shopSupplierFuture(int n) {
        return () -> {
            List<CompletableFuture<Shop>> shopsFuture =
                    IntStream.rangeClosed(1, n).boxed()
                            .map(i -> CompletableFuture.supplyAsync(
                                    () -> generateRandomShop(),shopExecutorSupplier(n).get()
                            ))
                            .collect(Collectors.toList());
            List<Shop> result = shopsFuture.stream()
                                            .map(CompletableFuture::join)
                                            .collect(Collectors.toList());
            return result;
        };
    }

    private static Supplier<Executor> shopExecutorSupplier(int n){
        return () -> {
            final Executor executor = Executors.newFixedThreadPool(Math.min(n, 100), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t;
                }
            });
            return executor;
        };
    }

    /**
     * 生成一个包含随机字符串的商店
     *
     * @return
     */
    private static risiblefish.java8.chapter_11.sub_11_4.Shop generateRandomDiscountShop() {
        String randomKey = "risiblefishstruggleforlife";
        Random random = new Random();
        int len = randomKey.length();
        int n = random.nextInt(len);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(randomKey.charAt(random.nextInt(len)));
        }
        return new risiblefish.java8.chapter_11.sub_11_4.Shop(sb.toString());
    }

    /**
     * 生成一个包含随机字符串的商店
     *
     * @return
     */
    private static Shop generateRandomShop() {
        String randomKey = "risiblefishstruggleforlife";
        Random random = new Random();
        int len = randomKey.length();
        int n = random.nextInt(len);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append(randomKey.charAt(random.nextInt(len)));
        }
        return new Shop(sb.toString());
    }

    public static void main(String[] args) throws InterruptedException {
        long start1 = System.nanoTime();
        List<Shop> shops1 = shopSupplierAsync(100).get();
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs");
        System.out.println("shops1: " + shops1.size());

        long start2 = System.nanoTime();
        List<Shop> shops2 = shopSupplierParallel(100).get();
        long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration2 + " msecs");
        System.out.println("shops2: " + shops2.size());

        long start3 = System.nanoTime();
        List<Shop> shops3 = shopSupplierFuture(100).get();
        long duration3 = (System.nanoTime() - start3) / 1_000_000;
        System.out.println("Done in " + duration3 + " msecs");
        System.out.println("shops3: " + shops3.size());

        //todo 遗留问题：为什么start3的性能不好？


    }
}
