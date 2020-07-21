package risiblefish.java8.chapter_3.sub_3_6;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-14 12:57
 **/

/**
 * 构造器有3个入参
 */
public class TriParamObject {
    private String name;
    private int age;
    private boolean isAnimal;

    public TriParamObject(String name, int age, boolean isAnimal) {
        this.name = name;
        this.age = age;
        this.isAnimal = isAnimal;
    }

    @Override
    public String toString() {
        return "TriParamObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isAnimal=" + isAnimal +
                '}';
    }
}
