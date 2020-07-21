package risiblefish.java8.chapter_2.sub_2_4;

import risiblefish.java8.chapter_2.sub_2_1.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-13 21:16
 **/


public class CompareUsingLambda {
    /**
     * 场景：有一堆苹果库存，有2个业务需求，分别要对苹果按照颜色字母/重量进行排序
     */

    /**
     * 做法是，Collections.sort(传入定制的Comparator)，也就是sort的行为由Comparator对象来参数化
     */
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(100, "red"),
                new Apple(160, "green"),
                new Apple(170, "yellow"));
        //传统做法： 实现comparator接口的方法，按重量升序排序
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return (int)(o1.getWeight() - o2.getWeight());
            }
        });
        System.out.println(inventory);

        //用lambda表达式，来实现按重量降序排序
        inventory.sort((Apple o1, Apple o2) -> (int)(o2.getWeight() - o1.getWeight()));
        System.out.println(inventory);
    }
}
