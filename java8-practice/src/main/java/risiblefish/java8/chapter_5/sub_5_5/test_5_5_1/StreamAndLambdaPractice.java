package risiblefish.java8.chapter_5.sub_5_5.test_5_5_1;

import risiblefish.java8.chapter_5.sub_5_5.Trader;
import risiblefish.java8.chapter_5.sub_5_5.Transaction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-16 10:21
 **/

public class StreamAndLambdaPractice {

    /**
     * (1) 找出2011年发生的所有交易，并按交易额排序(从低到高)。
     * (2) 交易员都在哪些不同的城市工作过?
     * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
     * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
     * (5) 有没有交易员是在米兰工作的?
     * (6) 打印生活在剑桥的交易员的所有交易额。
     * (7) 所有交易中，最高的交易额是多少?
     * (8) 找到交易额最小的交易。
     */

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        /** (1) 找出2011年发生的所有交易，并按交易额排序(从低到高)。*/
        List<Transaction> list1 =
                transactions.stream()
                        .filter(t -> 2011 == t.getYear())
                        .sorted(Comparator.comparing(Transaction::getValue))
                        .collect(Collectors.toList());
        System.out.println(list1);

        /** (2) 交易员都在哪些不同的城市工作过? */
        List<String> list2 =
                traders.stream()
                        .map(Trader::getCity)
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(list2);

        /**
         *  (3) 查找所有来自于剑桥的交易员，并按姓名排序。
         */
        List<Trader> list3 =
                traders.stream()
                        .filter(t -> "Cambridge".equals(t.getCity()))
                        .sorted(Comparator.comparing(Trader::getName))
                        .collect(Collectors.toList());
        System.out.println(list3);

        /**
         *  (4) 返回所有交易员的姓名字符串，按字母顺序排序。
         */
        List<String> list4 =
                traders.stream()
                        .sorted(Comparator.comparing(Trader::getName))
                        .map(Trader::getName)
                        .collect(Collectors.toList());
        System.out.println(list4);

        /**
         *  (5) 有没有交易员是在米兰工作的?
         */
        Boolean traderInMilan =
                traders.stream()
                        .anyMatch(t -> "Milan".equals(t.getCity()));
        System.out.println(traderInMilan);

        /**
         *  (6) 打印生活在剑桥的交易员的所有交易额。
         */
        Integer tradeSum =
                transactions.stream()
                        .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                        .map(Transaction::getValue)
                        .reduce(0, Integer::sum);
        System.out.println(tradeSum);

        /**
         *  (7) 所有交易中，最高的交易额是多少?
         */
        Optional<Integer> maxValue =
                transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println(maxValue);

        /**
         * (8) 找到交易额最小的交易。
         */
        Optional<Transaction> minValTransaction =
                transactions.stream()
                        .sorted(Comparator.comparing(Transaction::getValue))
                        .findFirst();
        System.out.println(minValTransaction);

        /**
         *  思考： 如果交易额最小的交易不止一笔，怎么做？
         */
        List<Transaction> minValList =
                transactions.stream()
                        .filter(
                                t -> {
                                    Optional<Integer> minVal = transactions.stream()
                                                    .map(Transaction::getValue)
                                                    .reduce(Integer::min);
//                                    System.out.println(minVal);
                                    if (minVal.isPresent()) {
                                        return t.getValue() == minVal.get();
                                    }
                                    return false;
                                }
                            )
                        .collect(Collectors.toList());
        System.out.println(minValList);
    }
}
