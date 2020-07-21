package risiblefish.java8.chapter_2.sub_2_1;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-13 11:47
 **/

public class Apple {

    private double weight;

    private String color;

    public Apple(double weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
