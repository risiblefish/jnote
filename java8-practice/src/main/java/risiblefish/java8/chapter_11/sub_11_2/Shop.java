package risiblefish.java8.chapter_11.sub_11_2;

import risiblefish.java8.chapter_11.SimulateUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-24 14:51
 **/

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * 同步的查询价格的方法，会导致阻塞
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 计算货品价格的方法，会模拟引入3秒的延迟
     */
    private double calculatePrice(String product) {
        SimulateUtil.delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 自己实现completablefuture
     *
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futureprice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futureprice.complete(price);
            } catch (Exception e) {
                futureprice.completeExceptionally(e);
            }
        }).start();
        return futureprice;
    }

    /**
     * 上面的方法还可以使用CompletableFuture提供的工厂方法
     * supplyAsync方法接收的是一个Supplier，所以我们提供一个lambda，签名为 （）-> Double
     * 生产者方法calculatePrice会交给ForkJoinPool池中的某个执行线程（Executor）运行。
     * 你也可以使用supplyAsync的重载方法supplyAsync(Supplier<U> supplier,Executor executor)来指定线程
     */
    public Future<Double> getPriceAsyncByFactory(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    /**
     * 这么一个需求，同一件商品，我们需要知道它在不同商店的价格
     * 创建一个方法，接收一个product和商店列表，返回一个List，包含该product的名称，对应的商店，以及在该商店的价格
     */
    //使用同步方法顺序执行
    public static List<String> findPricesSync(String product, List<Shop> shops) {
        return shops.stream()
                .map(shop -> String.format("{shop : %s ,price : %.2f}", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 将顺序执行改为并行执行
     */
    //使用同步方法并行执行
    public static List<String> findPricesParallel(String product, List<Shop> shops) {
        return shops.parallelStream()
                .map(shop -> String.format("{shop : %s ,price : %.2f}", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 用completable future 异步查询
     */
    public static List<String> findPricesCF(String product, List<Shop> shops) {
        List<CompletableFuture<String>> pricesFuture =
                shops.stream().map(shop ->
                        CompletableFuture.supplyAsync(
                                () -> String.format("{shop : %s ,price : %.2f}"
                                        , shop.getName(), shop.getPrice(product))
                        )).collect(Collectors.toList());
        return pricesFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static Supplier<Executor> executorSupplier(List<Shop> shopList) {
        return () -> {
            final Executor executor = Executors.newFixedThreadPool(Math.min(shopList.size(), 100), new ThreadFactory() {
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
     * 用completable future 异步查询
     * 并且定制executor
     */
    public static List<String> findPricesCustomizeExecutor(String product, List<Shop> shops) {
        List<CompletableFuture<String>> pricesFuture =
                shops.stream()
                        .map(shop ->
                                CompletableFuture.supplyAsync(
                                        () -> String.format("{shop : %s ,price : %.2f}"
                                                , shop.getName(), shop.getPrice(product))
                                        , executorSupplier(shops).get()
                                ))
                        .collect(Collectors.toList());
        return pricesFuture.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }






    public static void main(String[] args) {
//        Shop shop = new Shop("BestShop");
//        long start = System.nanoTime();
//        Future<Double> futurePrice = shop.getPriceAsync("my favorivate product");
//        long invocationTime = (System.nanoTime() - start) / 1_000_000;
//        System.out.println("Invocation returned after " + invocationTime + " msecs");
//
//        //do something else
//        SimulateUtil.delay();
//        //
//        try {
//            double price = futurePrice.get();
//            System.out.printf("Price is %.2f%n", price);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        long retrievalTime = (System.nanoTime() - start) / 10_000_000;
//        System.out.println("Price returned after " + retrievalTime + " msecs");

        List<Shop> shops = SimulateUtil.shopSupplierParallel(100).get();

        long start1 = System.nanoTime();
        findPricesSync("myPhone27S", shops);
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs");

        long start2 = System.nanoTime();
        findPricesParallel("myPhone27S", shops);
        long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration2 + " msecs");

        long start3 = System.nanoTime();
        findPricesCF("myPhone27S", shops);
        long duration3 = (System.nanoTime() - start3) / 1_000_000;
        System.out.println("Done in " + duration3 + " msecs");

        long start4 = System.nanoTime();
        findPricesCustomizeExecutor("myPhone27S",shops);
        long duration4 = (System.nanoTime() - start4) / 1_000_000;
        System.out.println("Done in " + duration4 + " msecs");

    }
}
