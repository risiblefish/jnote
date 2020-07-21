package risiblefish.java8.chapter_5.sub_5_6;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-16 16:30
 **/

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 *  测试数值流
 */
public class IntStreamTest {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        /**
         *  映射到数值流： mapToInt()
         */
        OptionalInt minVal =
                numbers.stream()
                        .mapToInt(Integer::intValue)
                        .min();
        System.out.println(minVal.orElse(-1));

        /**  转回对象流：e.g. <Stream>Integer = ...mapToInt().boxed();*/
        Optional<Integer> minVal1 =
                numbers.stream()
                        .mapToInt(Integer::intValue)
                        .boxed()
                        .min(Integer::min);
        System.out.println(minVal1.orElse(-1));

        /**
         *  数值流的range() [a,b)
         *         rangeClosed [a,b]
         */
        long count1 = IntStream.range(1,100)
                .filter(i -> i % 2 == 0).count();
        System.out.println(count1);

        long count2 = IntStream.rangeClosed(2,100)
                .filter(i -> i%2 == 0).count();
        System.out.println(count2);
    }
}
