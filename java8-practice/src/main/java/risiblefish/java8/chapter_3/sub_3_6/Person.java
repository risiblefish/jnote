package risiblefish.java8.chapter_3.sub_3_6;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-14 12:48
 **/

/**
 * 构造器有1个入参
 */
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
