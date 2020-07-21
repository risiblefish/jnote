package risiblefish.java8.chapter_6.sub_6_2;

import risiblefish.java8.chapter_4.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-17 22:29
 **/

public class ComparingReduceAndCollect {
    /**
     * 到此，我们学了关于归约的不少内容，我们可能会想，Stream接口的reduce和collect有嘛区别呢？
     * 因为这2种方法通常会返回相同的结果
     */
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> numbers = stream.reduce(
                new ArrayList<Integer>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                });
        System.out.println(numbers);
    }
}
