package risiblefish.java8.chapter_5.sub_5_5;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-16 10:10
 **/

public class Trader{
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
