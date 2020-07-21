package risiblefish.java8.chapter_6.sub_6_4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-18 11:32
 **/

public class PartitionTest {
    /**
     *  练习：将前n个自然数按照质数和非质数分组
     */

    public static boolean isPrime(int curr) {
        int root = (int)Math.sqrt(curr);
        return IntStream.rangeClosed(2,root).noneMatch(i -> curr % i == 0);
    }

    public static List<Integer> primeList(int n) {
        return IntStream.rangeClosed(2,n)
                .boxed()
                .collect(Collectors.partitioningBy(curr -> isPrime(curr)))
                .get(true);
    }

    public static void main(String[] args) {
        System.out.println(primeList(100));
    }
}
