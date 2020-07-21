package risiblefish.java8.chapter_3.sub_3_8;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-14 21:48
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 将Function进行复合
 */
public class ComplexFunction {

    public static void main(String[] args) {
        //f(x) = x + 1
        Function<Integer, Integer> fx = (x) -> x + 1;
        //g(x) = x * x
        Function<Integer, Integer> gx = (x) -> x * x;

        /**
         * 如果我们想把f(x)的值传入g(x)进行运算
         * Function提供了andThen
         */
        //g(f(x))
        Function<Integer,Integer> gfx = fx.andThen(gx);

        /**
         *  如果我们想先计算g(x)，再放入f(x)
         *  Function提供了compose
         *
         */
        //f(g(x))写法1
        Function<Integer,Integer> fgx = fx.compose(gx);

        //f(g(x))写法2
        Function<Integer,Integer> fgx2 = gx.andThen(fx);

        /**
         * 测试
         */
        int i = 3;
        List<Integer> result = Arrays.asList(fx.apply(i),gx.apply(i),gfx.apply(i),fgx.apply(i),fgx2.apply(3));
        result.forEach(System.out::println);
    }
}
