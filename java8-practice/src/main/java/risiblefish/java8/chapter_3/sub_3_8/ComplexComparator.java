package risiblefish.java8.chapter_3.sub_3_8;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-14 20:54
 **/

import risiblefish.java8.chapter_2.sub_2_1.Apple;
import risiblefish.java8.chapter_3.sub_3_7.MyApple;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;


/**
 *  将Comparator进行复合
 */
public class ComplexComparator {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(1,"red"),
                                              new Apple(2,"yellow"),
                                              new Apple(3,"green"));


        //对苹果重量进行排序
//        inventory.sort(
//                (Apple o1, Apple o2) -> {
//                    return Double.valueOf(o1.getWeight()).compareTo(o2.getWeight());
//                }
//        );
        inventory.sort(comparing(Apple::getWeight));

        /**
         * 如果我们需要对苹果重量逆序排序怎么办
         * 和comparing一样，Comparator还提供了default方法reversed()
         */

        inventory.sort(comparing(Apple::getWeight).reversed());

        /**
         * 如果2个苹果一样重，我们希望再定一个规则，当重量一样时，按颜色字母自然序排序
         * Comparator还提供了default方法 thenComparing
         */
        inventory.sort(
                comparing(Apple::getWeight)
                        .reversed()
                        .thenComparing(Apple::getColor)
        );

    }
}
