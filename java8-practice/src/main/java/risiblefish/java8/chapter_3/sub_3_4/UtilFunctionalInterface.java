package risiblefish.java8.chapter_3.sub_3_4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-14 09:12
 **/

public class UtilFunctionalInterface {

    /**
     * java的库中有很多函数式接口，其中pradicate,consumer,function都很常见，它们的抽象方法签名分别是：T -> boolean, T->void, T->R
     * @param args
     */
    public static void main(String[] args) {
        Predicate<String> judgeEmptyString = (String s) -> s.isEmpty();

        Consumer<List> printList = (List l) -> System.out.println(l);

        Function<String,Integer> intParser = (String s) -> Integer.parseInt(s);

        List<String> list = Arrays.asList("d","c","b","a");
        list.sort(String::compareTo);
        System.out.println(list);
    }




}
