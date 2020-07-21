package risiblefish.java8.chapter_6.sub_6_5;

import risiblefish.java8.chapter_4.Dish;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collector.Characteristics.CONCURRENT;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-18 19:38
 **/

/**
 * 练习：
 * 自己实现一个Collector
 * 这里为了便于演示以及熟悉接口，我们把累加器和最终输出使用不同类型的List
 */
public class ToListCollector<T> implements Collector<T, LinkedList<T>, ArrayList<T>> {
    @Override
    /**
     * 它是一个生产者，生产累加器的处理容器，这里用LinkedList
     */
    public Supplier<LinkedList<T>> supplier() {
        return () -> new LinkedList<T>();
    }

    @Override
    /**
     *  它是一个BiConsumer类型的累加器，用于消费中间值
     *  其中，public interface BiConsumer<T, U> （T,U）-> void
     *  BiConsumer有2个入参，第1个入参是保存规约结果的累加器（已收集了流中前n-1个项目），第2个入参是第n个元素
     *  在这里，我们要把每个T对象放到List里
     */
    public BiConsumer<LinkedList<T>, T> accumulator() {
        return (list, currT) -> list.add(currT);
        /**
         * 这里为什么要加return ？
         * 答：
         * BiConsumer对入参的消费是在副作用里。
         * 我们这里其实是重写了default方法
         *     default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
         *         Objects.requireNonNull(after);
         *
         *         return (l, r) -> {
         *             accept(l, r);
         *             after.accept(l, r);
         *         };
         * 所以，我们要返回一个消费/规约函数。
         */
    }

    /** 思考：为什么不能把2个入参顺序换掉？
     *  比如：public BiConsumer<T,LinkedList<T>> accumulator() {...}
     *  答：
     *  因为我们实现的是Collector<T, A, R>这个接口
     *  这个接口下的accumulator方法必须是BiConsumer<A,T>类型的
     *  代入Collector<T, List<T>,List<T>>
     *  A就是List<T>, T就是T
     */

    /**
     * 使用累加器遍历消费完流之后，我们需要将累加器对象转换为最终输出的对象
     * <p>
     * 接口定义：
     * Collector<T, A, R>
     * Supplier<A> supplier();
     * Function<A, R> finisher();
     * <p>
     * 即最后输出的是R对象
     * Collector<T, LinkedList<T>,ArrayList<T>>
     * 所以我们要把LinkedList -> ArrayList
     */
    @Override
    public Function<LinkedList<T>, ArrayList<T>> finisher() {
        return (linkedList) -> new ArrayList<>(linkedList);
    }

    /**
     *  到这里，其实已经可以完成任务了，但是整个处理是顺序执行的，
     *  有没有办法利用硬件的多核从而提高处理效率呢？
     *
     *  Collector的combiner方法提供了这种可能
     *  combiner方法会返回一个供归约操作使用的函数，
     *  它定义了对流的各个子部分进行并行处理时，各个子部分归约所得的累加器要[如何合并]
     */

    /**
     * public interface BinaryOperator<T> extends BiFunction<T,T,T>
     * 从BinaryOperator的定义可以看到它是BiFunction的扩展，额外提供了minBy和maxBy
     */
    @Override
    public BinaryOperator<LinkedList<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };//这里为什么要加分号？ 答：这里的分号是对应return的，可以这么看 return lambda-exp ;

    }


    /**
     * 最后一个方法——characteristics会返回一个不可变的Characteristics集合，
     * 它定义了收集器的行为——尤其是关于流是否可以并行归约，以及可以使用哪些优化的提示。
     *
     * Characteristics是一个包含三个项目的枚举。
     * (1) UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响。
     * (2) CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。
     * 如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约。
     *
     * (3) IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。
     * 这种情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器LinkedList不加检查地转换为结果ArrayList是安全的。
     *
     * 我们迄今开发的ToListCollector是IDENTITY_FINISH的，因为用来累积流中元素的List已经是我们要的最终结果，用不着进一步转换了，
     * 但它并不是UNORDERED，因为用在有序 流上的时候，我们还是希望顺序能够保留在得到的List中。
     *
     * 最后，它是CONCURRENT的，但我们刚才说过了，仅仅在背后的数据源无序时才会并行处理。
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
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
                new Dish("salmon", false, 450, Dish.Type.FISH));

        //使用我们自定义的collector来拿到list
        List<Dish> list1 = menu.stream().collect(new ToListCollector<Dish>());
        System.out.println(list1);

        //使用库的collect(toList())
        List<Dish> list2 = menu.stream().collect(Collectors.toList());
        System.out.println(list2);

        /**
         * 弄懂Collector做的事情之后，Stream还提供了一个重载的collect方法，它接收3个参数：[supplier],[accumulator],[combiner]
         * 我们可以传入这3个入参来实现同样的目的
         */
        List<Dish> list3 = menu.stream().collect(ArrayList::new,List::add,List::addAll);
        System.out.println(list3);
    }
}
