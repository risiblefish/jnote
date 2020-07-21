package risiblefish.java8.chapter_5.sub_5_2.test_5_2;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-15 21:51
 **/

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class Squares {
    public static void main(String[] args) {

        /**
         * 练习1： 输入一组数，输出一组数，输出的数中每个位置上的数都是输入数组该位置上的数的平方
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> squares =
                numbers.stream()
                        .map(n -> n * n)
                        .collect(toList());
        System.out.println(squares);


        /**
         * 练习2 ： 给定2组数，返回所有的数对，比如{1，2}和{3,4},返回{[1,3],[1,4],[2,3],[2,4]}
         */
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<int[]> totalPairs =
                list1.stream()
                        .flatMap(i -> list2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());
        totalPairs.forEach(a -> System.out.print(Arrays.toString(a) + " "));

        /**
         *  自问: 如果不用flatMap，而是用map，会生成什么？
         *          List result2 =
         *                 list1.stream()
         *                         .map(i -> list2.stream()
         *                                 .map(j -> new int[]{i, j})
         *                         )
         *                         .collect(toList());
         *         System.out.println();
         *
         *  答：
         *  可以这么来看，map接收的是一个Function<T,R>,也就是签名是T -> R
         *  而stream()是转换为流，即List<Integer> -> Stream<Integer>
         *
         *  然后使用map进行迭代，可以简单地理解为把每一个元素变成一个独立的流
         *  （1）第2个map
         *    list2.stream().map(j -> new int[]{i,j}
         *    在 Integer j -> Integer[] 套上Stream<>
         *    即：(Stream<Integer> j) -> Stream<Integer[]>
         *
         *  （2）回到第1个map，它套在第2个map之上，就变成了
         *   Integer i -> Stream<Integer[]> 套上Stream<>
         *   即： (Stream<Integer> i)  -> Stream<Stream<Integer[]>>
         *
         *   所以2次都用map的话，拿到的是 Stream<Stream<Integer[]>>，我们需要用flatMap来让它扁平化
         */

        /**
         *  练习3 ： 给定2组数，在所有组合的数对中，返回总和能被3整除的数对
         *  如：{1,2,3},{3,4} 所有数对 {[1,3],[1,4],[2,3],[2,4],[3,3],[3,4]}
         *  其中总和能被3整除的有 {[2,4],[3,3]}
         */
        List<Integer> list3 = Arrays.asList(1, 2, 3);
        List<Integer> list4 = Arrays.asList(3, 4);

        List<int[]> triPairs =
                list3.stream()
                        .flatMap(i -> list4.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                        ).collect(toList());
        triPairs.forEach(a -> System.out.print(Arrays.toString(a) + " "));
    }
}
