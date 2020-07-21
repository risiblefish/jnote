package risiblefish.java8.chapter_5.sub_5_3.test_5_3;

import risiblefish.java8.chapter_4.Dish;

import java.util.Arrays;
import java.util.List;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-15 23:27
 **/

public class CountDishes {

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
         *  练习： 用reduce和map统计菜单中有几道菜
         *
         *  其中reduce接收的BinaryOperator 如下：
         *
         *  public interface BinaryOperator<T> extends BiFunction<T,T,T>
         *
         *  它继承了BiFunction接口，函数签名为 T,T -> T， 即传入2个同类型的对象，返回1个同类型的对象
         */
        int count =
                menu.stream()
                        .map(d -> 1) //把每道菜都映射为1，即 Dish -> Integer
                        .reduce(0, (a, b) -> a + b); //然后规约做加法
        System.out.println(count);
    }
}
