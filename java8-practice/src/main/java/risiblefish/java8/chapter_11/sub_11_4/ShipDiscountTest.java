package risiblefish.java8.chapter_11.sub_11_4;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-25 13:07
 **/

import risiblefish.java8.chapter_11.SimulateUtil;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 练习： 对多个异步任务进行流水线处理
 * 假设现在商店提供一个折扣服务，可以查询某件product对应的折扣以及折后的价格
 * 假设所有商店的getPrice方法都返回一串字符串，这个字符串体现了商店的名字，商品折后的价格，以及相应的折扣
 */
public class ShipDiscountTest {

    private static Supplier<Executor> executorSupplier(List<Shop> list) {
        return () -> {
            final Executor executor = Executors.newFixedThreadPool(Math.min(list.size(), 100), new ThreadFactory() {
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
     * 最简单的查询折后价格的getPrice方法
     */
    public static List<String> findPricesSync(List<Shop> shopList, String product) {
        return shopList.stream()
                .map( shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 使用CompletableFuture的查询折后价格的getPrice方法
     */
    public static List<String> findPricesCF(List<Shop> shopList, String product) {
        Executor executor = executorSupplier(shopList).get();
        List<CompletableFuture<String>> priceFutures =
                shopList.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product),executor
                        ))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote),executor
                        )))
                        .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /**
     * 需求：当有一家商店返回价格时，就立即显示价格，而不再等待所有商店查询完毕
     *
     * 要做到这一点，在前面的代码基础上，我们应该避免创建一个需要等待所有price的list，而是直接处理流，并且在每个completablefuture上增加一个操作，
     * 这个操作可以直接使用future的返回值，CompletableFuture.thenAccept提供了这样的功能
     *
     */

    public static void showPriceASAP(List<Shop> shopList, String product) {
        findPricesStream(shopList,product)
                .map(f -> f.thenAccept(System.out::println));
    }

    private static Stream<CompletableFuture<String>> findPricesStream(List<Shop> shopList, String product){
        Executor executor = executorSupplier(shopList).get();
        return shopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product),executor
                ))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote),executor
                        )
                ));
    }




    public static void main(String[] args) {

        List<Shop> shops = SimulateUtil.discountShopSupplierParallel(4).get();

        long start1 = System.nanoTime();
        System.out.println(findPricesSync(shops,"aj"));
        long duration1 = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in " + duration1 + " msecs");


        long start2 = System.nanoTime();
        System.out.println(findPricesCF(shops,"aj"));
        long duration2 = (System.nanoTime() - start2) / 1_000_000;
        System.out.println("Done in " + duration2 + " msecs");
    }
}
