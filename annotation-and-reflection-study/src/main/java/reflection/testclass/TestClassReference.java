package reflection.testclass;

/**
 * 测试类什么时候会被加载
 * @author Sean Yu
 */
public class TestClassReference {
    static {
        System.out.println("main类被加载");
    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //1 主动引用
//        testclass.Son son = new testclass.Son();

        //2 反射也会产生主动引用
//        Class.forName("testclass.Son");
//        System.out.println(testclass.Son.class); //直接打印子类不会引起父类加载

        //3 子类调用父类的静态变量不会引起子类加载
//        System.out.println(testclass.Son.b);

        //4 定义数组类引用 也不会引发类的初始化
//        testclass.Son[] arr = new testclass.Son[5];

        //5 调用子类的常量，也不会引起父类和子类的初始化
        System.out.println(Son.M);
    }
}

class Father{
    static int b = 2;
    static {
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static {
        System.out.println("子类被加载");
    }

    static int m = 100;
    static final int M = 1;
}
