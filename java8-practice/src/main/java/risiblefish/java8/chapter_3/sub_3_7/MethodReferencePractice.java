package risiblefish.java8.chapter_3.sub_3_7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import static java.util.Comparator.comparing;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-14 13:11
 **/

public class MethodReferencePractice {
    /**
     *  目标： inventory.sort(comparing(MyApple::getWeight))
     *
     *  其中，sort的签名如下：
     *  void sort(Comparator<? super E> C)
     *  它需要一个Comparator对象来比较两个MyApple，
     *  这就是策略模式，2个MyApple对象必须包裹在1个对象里，让sort行为参数化： 传给sort的策略不同，其行为就会不同
     */

    /**
     * 经典的实现 : 自己写一个Comparator的实现类
     */
    public class MyAppleComparator implements Comparator<MyApple> {
        @Override
        public int compare(MyApple o1, MyApple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }

    public static void main(String[] args) {
        List<MyApple> inventory = Arrays.asList(new MyApple("1"),
                                                new MyApple("2"),
                                                new MyApple("3"));
        /**
         * 我们来改进它，使用匿名类，而不是实现这个接口却只调用它一次
         */
        inventory.sort(new Comparator<MyApple>() {
            @Override
            public int compare(MyApple o1, MyApple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        /**
         * 让我们继续改进它，可以看到Comparator的函数签名为(T,T) -> int 在这里为（MyApple,MyApple）-> int
         * 我们用lambda通过传递代码的方式来实现它
         */
        inventory.sort(
                (MyApple o1, MyApple o2) -> o1.getWeight().compareTo(o2.getWeight())
        );

        /**
         * 由于java编译器可以根据上下文推断目标类型，我们可以进一步简写
         */
        inventory.sort(
                (o1, o2) -> o1.getWeight().compareTo(o2.getWeight())
        );

        /**
         * 能不能再简单一点？ Comparator具有一个叫做comparing的静态辅助方法，
         * 它接收一个Function<MyApple,Comparator>, 知道了要比较的类，以及返回1个Comparator对象： (MyApple) -> (Comparator)
         *
         *     public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
         *             Function<? super T, ? extends U> keyExtractor)
         *     {
         *         Objects.requireNonNull(keyExtractor);
         *         return (Comparator<T> & Serializable)
         *             (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
         *     }
         * 所以我们使用comparing方法，只需定义比较规则，像下面这样
         */
        Comparator<MyApple> c = Comparator.comparing(
                (MyApple a) -> a.getWeight()
        );

        /**
         * 再进一步，使用匿名类实现Comparator
         */
        //import static java.util.Comparator.comparing;
        inventory.sort(
                comparing(
                        (a) -> a.getWeight()
                )
        );

        /**
         * 最后，我们使用方法引用进行最后的简化
         */
        inventory.sort(comparing(MyApple::getWeight));
    }
}
