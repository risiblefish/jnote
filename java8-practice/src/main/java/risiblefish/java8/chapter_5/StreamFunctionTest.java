package risiblefish.java8.chapter_5;

import risiblefish.java8.chapter_4.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-15 11:14
 **/

public class StreamFunctionTest {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        /**
         * 用谓词筛选
         * Stream接口支持filter()方法，filter()接收一个谓词（返回boolean的函数），返回一个包含所有符合谓词的元素的流
         * 比如，我们想创建一个包含所有肉食的菜单
         */
        List<Dish> meatDishes =
                menu.stream()
                        .filter((a) -> Dish.Type.MEAT.equals(a.getType()))
                        .collect(Collectors.toList());
        System.out.println(meatDishes);

        /**
         * 筛选各异的元素
         * Stream接口支持distinct()方法，确保没有重复
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 4, 4, 4, 6, 7, 8, 9, 10, 10);
        //打印出所有没有重复的偶数
        numbers.stream()
                .filter((a) -> a % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        /**
         * 截短流
         * Stream接口支持limit(n)方法，返回一个长度不超过n的流
         * limit可以作用于有序流和无序流
         */
        //打印出热量最高的三道菜
        menu.stream()
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                .limit(3).forEach(System.out::println);

        /**
         * 跳过元素
         * Stream接口支持skip(n)方法，无视流中的前n个元素，返回剩下的元素，如果不足n个，则会返回1个空流
         */
        //打印出按热量排序的去掉前2位的菜单
        menu.stream()
                .sorted(Comparator.comparing(Dish::getCalories))
                .skip(2)
                .forEach(System.out::println);
    }
}
