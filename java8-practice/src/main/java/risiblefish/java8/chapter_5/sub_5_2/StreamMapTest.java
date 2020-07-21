package risiblefish.java8.chapter_5.sub_5_2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-15 18:28
 **/

public class StreamMapTest {
    public static void main(String[] args) {
        List<String> wordsList = Arrays.asList("hello","world");

        /**
         * 假设现在有一个需求，返回一个不包含重复字母的列表
         */

        //第1次尝试
        List list1 =
                wordsList.stream()
                        .map(word -> word.split(""))
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(list1);

        /**
         * 第1次尝试失败，发现结果并不是我们想要的，这是因为String.split是把String -> String[]
         * 中间操作产生的Stream是Stream<String[]> 而我们想要的是Stream<String>
         * Arrays.stream()方法，可以接受一个数组，并产生一个流
         */

        //第2次尝试
        List list2 =
                wordsList.stream()
                        .map(word -> word.split(""))
                        .map(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(list2);

        /**
         *  第2次尝试失败，仍然不是我们想要的Stream<String>
         *  虽然数组里的每一个元素化成了流，但是仍是分开表示的，我们需要把它们统一到一个集合里
         *  Stream接口提供了flatMap方法，把多个流统一到一个流里
         */

        List list3 =
                wordsList.stream()
                        .map(word -> word.split(""))
                        .flatMap(Arrays::stream)
                        .distinct().collect(Collectors.toList());
        System.out.println(list3);

    }
}
