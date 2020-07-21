package risiblefish.java8.chapter_2.sub_2_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: java8
 * @description: see page 11
 * @author: Unuts
 * @create: 2020-03-13 11:45
 **/

public class AppleFilter {

    /**
     *  假设有需要对库存里的绿色苹果
     */

    //找出绿色的苹果
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * 这时新增了一个需求，还要筛选出黄色的苹果
     * 这时我们改进一下，把颜色作为参数，避免重复代码
     * @param inventory
     * @Param color
     * @return
     */
    //按入参color来找苹果
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 这时新增了一个需求，能不能按重量来筛选苹果
     * 这时，我们添加了一个以入参为重量的筛选方法
     * @param inventory
     * @param weight
     * @return
     */
    //按入参weight来找苹果
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 此时，停下来，想一想，如果又来了新需求怎么办呢，比如找颜色为红色，重量大于150g的苹果
     * 甚至再加点筛选条件，比如还要有花纹的，没有虫被咬过的，产地是国内的
     * 如果按现在这个写法，就要复制大量的重复代码，并且每次新需求一来，我们就要新写一个filter
     * 有没有办法不改动filter，动态地对每个属性做筛选？
     */

    /**
     * 这个时候，我们可以退后一步，看看更高层次的抽象：
     *   我们考虑的对象是苹果，我们需要根据苹果的一些属性（颜色，重量，是否有花纹，产地等）来返回一个boolean值
     * 我们把它称为谓词/predicate （即返回一个boolean值的函数）
     * 所以我们可以定义一个谓词接口
     */

    //Apple谓词接口
    public interface ApplePredicate {
        boolean test(Apple apple);
    }

    //获取大于150g的苹果
    public class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    //获取绿苹果
    public class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    /**
     * 这个谓词接口就是让行为参数化，让方法接收不同的行为参数，来完成不同的行为
     * 所以，接下来我们在filter方法里传入谓词
     */

    //谓词对象封装了测试苹果的条件
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 到此，我们终于可以不用改filter代码也能应对任何需求了：
     * 对于新来的需求，只需要写一个ApplePredicate的实现类
     * 但我们还是需要写多个实现类，然后在filter里new一个出来，有没有更省力的做法？
     * 答案就是使用匿名类
     */

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(100, "red"),
                new Apple(160, "green"),
                new Apple(170, "yellow"));

        //使用匿名类
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        /**
         * 不过，匿名类的使用，仍然显得冗余，其中包含了不少模板代码，比如要new一下，还要重写方法
         * 现在尝试用java8的lambda表达式来重写
         */

        //使用lambda表达式
        List<Apple> result = filterApples(inventory,(Apple apple) -> "red".equals(apple.getColor()));

        /**
         * 这样就清爽了很多，我们可以在能够使用lambda表达式实现的接口上，加上@FuncionalInterface
         */

        printEven();
    }

    /**
     * 现在，让我们在抽象化的路上，再进一步，前面的filterApples只能适用于Apple类，我们可以将List类型抽象化，从而处理更多的问题
     */
    //将谓词改为使用泛型
    public interface Predicate<T>{
        boolean test(T t);
    }

    //筛选具备某些条件的T
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T t : list) {
            if(p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 现在，我们可以用这个接口来实现Apple之外的需求，比如找出偶数
     */
    public static void printEven(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        List<Integer> list = filter(numbers,(Integer i) -> i % 2 == 0);
        System.out.println(list);
    }
}
