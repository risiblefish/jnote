package risiblefish.java8.chapter_2.sub_2_1.test_2_1;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-13 20:00
 **/

import risiblefish.java8.chapter_2.sub_2_1.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目: 对库存里的苹果（List）进行定制化输出，比如只打印其中重的苹果，或打印每只苹果，并输出其颜色
 */
public class Test_2_1 {

    public static void printCustomizedApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            System.out.println(formatter.accept(apple));
        }
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(100, "red"),
                                              new Apple(160, "green"),
                                              new Apple(170, "yellow"));
        printCustomizedApple(inventory, new AppleSimpleFormatter());
    }
}
