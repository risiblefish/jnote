package risiblefish.java8.chapter_6;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-17 20:09
 **/

public class MyTransaction {

    private Currency currency;

    public MyTransaction(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public enum Currency {
        CNY,DOLLAR,POUND
    }
}
