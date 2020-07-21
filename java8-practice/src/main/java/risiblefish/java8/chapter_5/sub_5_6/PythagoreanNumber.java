package risiblefish.java8.chapter_5.sub_5_6;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-16 19:21
 **/

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 生成勾股数
 */
public class PythagoreanNumber {

    /**
     * 在[1,100] 中找出所有勾股数对，如 a*a + b*b = c*c，则（a,b）是一对勾股数对
     */
    public static void main(String[] args) {

        Stream<double[]> result =
                IntStream.rangeClosed(1, 100)
                        .boxed()
                        .flatMap(i -> {
                                    return IntStream.rangeClosed(i, 100)
                                            .mapToObj(
                                                    j -> new double[]{i , j, Math.sqrt(i * i + j * j)})
                                            .filter(arr -> arr[2] % 1 == 0);
                                }
                        );
        result.forEach(t -> System.out.println(t[0] + " " + t[1] + " " + t[2]));

    }
}
