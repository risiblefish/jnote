package risiblefish.java8.chapter_3.sub_3_8;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-14 21:23
 **/

import risiblefish.java8.chapter_2.sub_2_1.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 将谓词进行复合
 */
public class ComplexPredicate {

    public static <T> List<T> tFilter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(1, "red"),
                new Apple(2, "yellow"),
                new Apple(3, "green"));
        //找出红色的苹果
        Predicate<Apple> redApple = (a) -> "red".equals(a.getColor());
        List<Apple> redApples = tFilter(inventory, redApple);

        /**
         *  如果现在来个需求，要找出所以不是红色的苹果呢，
         *  Predicate提供了default方法negate
         */
        List<Apple> notRedApples = tFilter(inventory, redApple.negate());

        /**
         *  如果要找出又红又大于150g的苹果呢
         *  Predicate提供了default方法and
         */
        List<Apple> redHeavyApples =
                tFilter(inventory,
                        redApple
                                .and((a) -> a.getWeight() > 150));

        /**
         *  如果找出又红又大于150g的苹果或者绿色的苹果呢
         *  可以使用or
         */
        List<Apple> redHeavyApplesAndGreenApples =
                tFilter(inventory,
                        redApple
                                .and((a) -> a.getWeight() > 150)
                                .or((a) -> "green".equals(a.getColor()))
                );



    }
}
