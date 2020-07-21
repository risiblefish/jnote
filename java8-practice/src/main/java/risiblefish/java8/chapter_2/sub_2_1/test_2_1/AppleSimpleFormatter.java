package risiblefish.java8.chapter_2.sub_2_1.test_2_1;

import risiblefish.java8.chapter_2.sub_2_1.Apple;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-13 20:26
 **/

/**
 * 定制化输出接口的一个实现类
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return String.format("An apple of %s color and %s weight", apple.getColor(), apple.getWeight());
    }
}
