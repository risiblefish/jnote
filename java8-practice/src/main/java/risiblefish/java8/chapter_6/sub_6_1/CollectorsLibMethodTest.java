package risiblefish.java8.chapter_6.sub_6_1;

import risiblefish.java8.chapter_4.Dish;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: java8
 * @description: 了解Collectors库中的预定义的静态方法
 * @author: Unuts
 * @create: 2020-03-17 20:35
 **/

public class CollectorsLibMethodTest {

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
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        /**
         *  计算所有菜品的数量
         */
        //reduce
        int count1 = menu.stream().map(t -> 1).reduce(0, (a,b) -> a+b);
        System.out.println(count1);

        //using collect
        long count2 = menu.stream().map(t -> t.getCalories()).collect(Collectors.counting());
        System.out.println(count2);

        /**
         *  找到热量最高的那道菜
         */

        //先按热量排序，然后找到第1个
        Optional<Dish> mostCaloric1 = menu.stream()
                                         .sorted(Comparator.comparing(Dish::getCalories).reversed())
                                         .findFirst();
        System.out.println(mostCaloric1.get().getCalories());

        //使用collect
        Optional<Dish> mostCaloric2 = menu.stream()
                                          .collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        System.out.println(mostCaloric2.get().getCalories());

        //直接用stream().max
        Optional<Dish> mostCaloric3 = menu.stream().max(Comparator.comparing(Dish::getCalories));
        System.out.println(mostCaloric3.get().getCalories());



        /**
         *  统计所有菜单的热量
         */
        //使用map-reduce
        Optional<Integer> totalCalories1 = menu.stream().map(Dish::getCalories).reduce((c1,c2) -> c1 + c2);
        System.out.println(totalCalories1.get());

        //使用collectors.summingInt方法
        int totalCalories2 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories2);


        /**
         *  计算dish的平均热量
         */
        //使用collectors.averagingInt int -> double
        double avg1 = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avg1);

        /**
         * 我们还可以使用Collectors.intSummarizing来一次性得到最大值，最小值，平均值，总值
         */
        IntSummaryStatistics summaryStatistics =
                menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);

        /**
         * joining工厂方法返回的收集器会把对流中每一个对象应用toString方法得到的所有字符
         * 串连接成一个字符串
         *
         *  个人理解： map - joining
         */

        //获取所有菜单名拼接成的字符串
        String dishNamesWithoutSpace =
                menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(dishNamesWithoutSpace);

        //获取所有菜单名拼接成的字符串,并加以,分开
        String dishNames =
                menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(dishNames);


        /**
         * collectors.reducing
         * 其中，reducing的3个入参分别是[起始值]，[转换函数]，[BinaryOperator]
         */

        //统计所有dish总热量
        int totalCalories3 = menu.stream()
                .collect(Collectors.reducing(0,Dish::getCalories,(c1,c2) -> c1 + c2));


        /**
         *  单入参的reducing是3入参的reducing的特殊版本
         *  它把[起始值]设为流中的第1项，这里即为第1个遍历到的dish
         *  它把[转换函数]设为恒等函数（即：函数仅仅返回其入参），个人理解是输入一个dish，返回该dish
         *  如此以来，它的[起始值]有可能为空，因为menu.stream()可能是一个空流，所以单入参的reducing返回对象是一个Optional
         */
        //找到热量最高的dish
        Optional<Dish> mostCaloric = menu.stream().collect(Collectors.reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2
        ));

    }
}
