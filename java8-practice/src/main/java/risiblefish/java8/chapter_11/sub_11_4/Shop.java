package risiblefish.java8.chapter_11.sub_11_4;

import risiblefish.java8.chapter_11.SimulateUtil;

import java.util.Random;

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
     * 计算货品价格的方法，会模拟引入3秒的延迟
     */
    private double calculatePrice(String product) {
        SimulateUtil.delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        //随机选取一个折扣码
        Discount.Code code = Discount.Code.values()[new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public static void main(String[] args) {

    }
}

