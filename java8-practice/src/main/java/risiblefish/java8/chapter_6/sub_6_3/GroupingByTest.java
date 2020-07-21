package risiblefish.java8.chapter_6.sub_6_3;

import risiblefish.java8.chapter_4.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-18 09:12
 **/

public class GroupingByTest {

    public enum CalorieLevel {LOW, MEDIUM, HIGH}

    ;

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
         *  将菜按照能量小于[0,400)，[400,600)，[600,600+]进行分类
         *  groupingBy 单入参其实是 groupingBy(分组函数，toList())的简写
         */
        Map<CalorieLevel, List<Dish>> map1 =
                menu.stream().collect(Collectors.groupingBy(
                        (Dish d) -> {
                            int curr = d.getCalories();
                            if (curr < 400) {
                                return CalorieLevel.LOW;
                            } else if (curr < 600) {
                                return CalorieLevel.MEDIUM;
                            } else {
                                return CalorieLevel.HIGH;
                            }
                        }
                ));
        System.out.println(map1);

        /**
         * 将菜按照Type和CalorieLevel分组
         */
        Map<Dish.Type, Map<CalorieLevel, List<Dish>>> map2 =
                menu.stream().collect(Collectors.groupingBy(
                        Dish::getType, Collectors.groupingBy(
                                (Dish d) -> {
                                    int curr = d.getCalories();
                                    if (curr < 400) {
                                        return CalorieLevel.LOW;
                                    } else if (curr < 600) {
                                        return CalorieLevel.MEDIUM;
                                    } else {
                                        return CalorieLevel.HIGH;
                                    }
                                }
                        )
                ));
        System.out.println(map2);

        /**
         * 统计每个Type对应的dish数量
         */
        //groupingBy的第2个入参只要是Collector类型即可，不一定必须传groupingBy
        Map<Dish.Type, Long> map3 =
                menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        //使用reducing(初始值，转换函数，BinaryOperator)
        Map<Dish.Type, Integer> map4 =
                menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.reducing(
                        0, d -> 1, (d1, d2) -> 1 + 1)));

        System.out.println(map3);
        System.out.println(map4);

        /**
         *  统计每个Type中热量最高的那道菜
         */
        Map<Dish.Type, Optional<Dish>> map5 =
                menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))
                        ));
        System.out.println(map5);

        /**
         *  这个Map中的值是Optional，因为这是maxBy工厂方法生成的收集器的类型，
         *  但实际上， 如果菜单中没有某一类型的Dish，这个类型就不会对应一个Optional.empty()值，而且根本不会出现在Map的键中。
         *  groupingBy收集器只有在应用分组条件后，第一次在流中找到某个键对应的元素时才会把键加入分组Map中。
         *  这意味着Optional包装器在这里不是很有用，因为它不会仅仅因为它是归约收集器的返回类型而表达一个最终可能不存在却意外存在的值。
         *
         *  所以，有没有办法把这个Optional去掉呢？
         *
         *  这就要用到Collectors.collectingAndThen,它的作用是把收集器的结果转换为另一种类型
         */
        Map<Dish.Type, Dish> map6 =
                menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get
                                )));
        System.out.println(map6);

        /**
         *  前面也提到，groupingBy能接收Collector入参，
         *  除了maxBy,collectingAndThen,还可以与其他返回Collector入参的方法联合
         */
        //e.g. 统计每个Type对应的Dish的总热量
        Map<Dish.Type, Integer> map7 =
                menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.summingInt(Dish::getCalories)));
        System.out.println(map7);

        /**
         * 统计一个结果集，可以查看每个Type下面，有哪些CalorieLevel
         */
        Map<Dish.Type, Set<CalorieLevel>> map8 =
                menu.stream()
                        .collect(Collectors
                                .groupingBy(Dish::getType,
                                        Collectors.mapping(
                                                (Dish d) -> {
                                                    int curr = d.getCalories();
                                                    if (curr < 400) {
                                                        return CalorieLevel.LOW;
                                                    } else if (curr < 600) {
                                                        return CalorieLevel.MEDIUM;
                                                    } else {
                                                        return CalorieLevel.HIGH;
                                                    }
                                                }, Collectors.toSet()
                                        )
                                )
                        );
        System.out.println(map8);

        /**
         *  分区练习
         *  把菜单按素食和非素食分开
         */
        Map<Boolean,List<Dish>> map9 =
                menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(map9);

    }
}
