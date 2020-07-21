package risiblefish.java8.chapter_4;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-15 09:53
 **/

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream流の初体验
 */
public class StreamTest {

    /**
     * 假设现在有一个需求，给你一份包含所有菜品的菜单，让你给出一个低能量并且按能量升序排列的菜名列表
     */

    /**
     * java 8之前，我们可能会这么做：
     */
    public static List<String> getLowCaloricDishesNameBeforeJava8(List<Dish> menu) {
        //先找到能量低的菜
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        //再按能量排序
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        //再获取菜名
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }

        return lowCaloricDishesName;
    }

    /**
     * 而在java8中，我们会这么做
     */
    public static List<String> getLowCaloricDishesNameUsingJava8(List<Dish> menu) {
        List<String> lowCaloricDishesName =
                //如果想利用多核架构，可以把stream()替换为parallelStream()
                menu.stream()
                        .filter((d) -> d.getCalories() < 400)
                        .sorted(Comparator.comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(Collectors.toList());
        return lowCaloricDishesName;
    }


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
         * stream 只能消费一次
         */
        Stream<Dish> s = menu.stream();
        s.forEach(System.out::println);
        //s.forEach(System.out::println); //如果再打印一次会报错：stream has already been operated upon or closed


    }
}
