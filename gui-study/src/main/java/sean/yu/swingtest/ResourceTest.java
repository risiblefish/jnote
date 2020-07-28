package sean.yu.swingtest;

/**
 * @program: gui-study
 * @description:
 * @author: Unuts
 * @create: 2020-06-30 08:43
 **/

public class ResourceTest {
    public static void main(String[] args) {
        ResourceTest obj = new ResourceTest();
        System.out.println(obj.getClass().getResource("/"));
        System.out.println(obj.getClass().getResource("1.txt"));
    }
}

class TestClassLoader {
    public static void main(String[] args) {
        System.out.println(TestClassLoader.class.getResource("xxx.xml"));
        System.out.println(TestClassLoader.class.getResource("/xxx.xml"));
        System.out.println(TestClassLoader.class.getResource("/ARROW.jpg"));
        System.out.println();
        System.out.println(TestClassLoader.class.getClassLoader().getResource("xxx.xml"));
        System.out.println(TestClassLoader.class.getClassLoader().getResource("ARROW.jpg"));
        System.out.println(TestClassLoader.class.getClassLoader().getResource("/xxx.xml"));
    }
}