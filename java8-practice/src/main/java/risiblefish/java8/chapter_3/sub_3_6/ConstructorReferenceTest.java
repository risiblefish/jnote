package risiblefish.java8.chapter_3.sub_3_6;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-14 12:39
 **/

import risiblefish.java8.chapter_2.sub_2_1.Apple;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 测试构造函数引用
 */
public class ConstructorReferenceTest {
    public static void main(String[] args) {
        /**
         * 对于无参构造器，可以使用Supplier的签名 （）-> T
         */
        Supplier<Object> objectSupplier = Object::new;
        Object obj = objectSupplier.get();

        /**
         * 对于1个参数的构造器, 可以使用Function的签名： (T) -> R
         */
        Function<String,Person> personFunction = Person::new;
        Person person = personFunction.apply("yu");

        /**
         * 对于2个参数的构造器，可以使用BiFunction的签名： (T,U) -> R
         */
        BiFunction<Double,String,Apple> appleBiFunction = Apple::new;
        Apple apple = appleBiFunction.apply(150.0, "red");

        /**
         * 对于3个甚至多个参数的构造器，java没有现有函数式接口，应该怎么办呢
         * 答案就DIY
         */
        MyTriFunction<String,Integer,Boolean,TriParamObject> triFunction = TriParamObject::new;
        TriParamObject triParamObject = triFunction.apply("et",100,false);

        System.out.println(obj.toString());
        System.out.println(person.toString());
        System.out.println(apple.toString());
        System.out.println(triParamObject.toString());
    }
}
