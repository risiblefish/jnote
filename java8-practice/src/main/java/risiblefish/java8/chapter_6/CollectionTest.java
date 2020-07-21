package risiblefish.java8.chapter_6;

import risiblefish.java8.chapter_5.sub_5_5.Transaction;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-17 20:11
 **/

public class CollectionTest {


    /**
     *  对list按照Currency来分类到Map<Currency,List<MyTransaction>>
     */


    /**
     * 传统的做法
     */
    public static Map<MyTransaction.Currency,List<MyTransaction>>
    groupByCurrencyUsingClassicWay(List<MyTransaction> list){
        Map<MyTransaction.Currency,List<MyTransaction>> result = new HashMap<>();
        for(MyTransaction curr : list) {
            MyTransaction.Currency c = curr.getCurrency();
            if(result.containsKey(c)) {
                result.get(c).add(curr);
            }else{
                result.put(c,Arrays.asList(curr));
            }
        }
        return result;
    }

    /**
     * 使用collect
     */
    public static Map<MyTransaction.Currency,List<MyTransaction>>
    groupByCurrencyUsingCollect(List<MyTransaction> list){
        return list.stream()
                    .collect(
                            Collectors.groupingBy(MyTransaction::getCurrency)
                    );
    }


    public static void main(String[] args) {
        List<MyTransaction> list = Arrays.asList(new MyTransaction(MyTransaction.Currency.CNY)
                                                ,new MyTransaction(MyTransaction.Currency.DOLLAR)
                                                ,new MyTransaction(MyTransaction.Currency.POUND));

        //传统的做法：
        System.out.println(groupByCurrencyUsingClassicWay(list));

        //使用collect:
        System.out.println(groupByCurrencyUsingCollect(list));
    }
}
