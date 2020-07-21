package risiblefish.java8.chapter_5.sub_5_7;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-16 20:20
 **/

public class FibonacciGenerate {

    public static void main(String[] args) {
        /**
         * 使用无限流创造fibonacci数列前100个数
         */

        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1],t[0]+t[1]})
                .limit(10)
                .forEach(arr -> System.out.println(arr[0] + " " + arr[1]));

        /**
         *  还可以用genertate来处理，iterate是每次创建新的元祖，但使用generate则需要依赖一个可变的状态
         *  我们可以用Supplier来处理
         */
        IntSupplier fib = new IntSupplier() {
            private int previus = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = previus;
                int next = previus + current;
                previus = current;
                current = next;
                return oldPrevious;
            }
        };

        IntStream.generate(fib).limit(10).forEach(System.out::println);

    }
}
