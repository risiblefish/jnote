package reflection;

/**
 * @program: jnote
 * @description:
 * @author: Unuts
 * @create: 2020-07-26 10:04
 **/

public class TestReflection {
    public static void main(String[] args) throws ClassNotFoundException {
        //-------------------- 获取类的一些方法 ----------------
        Class c1 = Class.forName("reflection.User");
        Class c2 = User.class;
        Class c3 = new User().getClass();
        System.out.println(c1 == c2 && c2 == c3);
        //方法4只有基本类型的包装类可以使用
        Class c4 = Integer.TYPE;
        Class c5 = int.class;
        System.out.println(c5.toString());

         //---------------- 哪些类型可以有class ----------------
    }
}

class User {
    private String name;
    private int id;
    private int age;

    public User() {
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
