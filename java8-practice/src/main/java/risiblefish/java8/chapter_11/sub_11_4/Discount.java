package risiblefish.java8.chapter_11.sub_11_4;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-25 13:08
 **/

import static risiblefish.java8.chapter_11.SimulateUtil.delay;

/**
 * 以枚举类型定义的折扣代码
 */
public class Discount {
    public enum Code {

        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static String apply(double price, Code code) {
        //模拟延迟
        delay();
        return String.format("%.2f", price * (100 - code.percentage) / 100);
    }
}
