package reflection.testclass;

/**
 * @author Sean Yu
 */
public class MyClass {
    public String name;
    private int age;

    public MyClass() {
        name = "default";
        age = 0;
    }

    private MyClass(String name) {
        this.name = name;
    }

    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void myPrivateMethod(){

    }

    @Override
    public String toString() {
        return "MyClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
