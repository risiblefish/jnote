package reflection.testclass;

import java.util.Arrays;

/**
 * 获得Class的信息
 * @author Sean Yu
 */
public class TestClass {
    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException {
        Class c = Class.forName("testclass.MyClass");

        //获得类的名字
        //包名 + 类名
        prettyPrint("getName()");
        System.out.println(c.getName());
        //类名
        prettyPrint("getSimpleName()");
        System.out.println(c.getSimpleName());

        //获得类的属性
        //只能找到public属性
        prettyPrint("getFields()");
        Arrays.stream(c.getFields()).forEach(System.out::println);
        //可以找到全部属性
        prettyPrint("getDeclaredFields()");
        Arrays.stream(c.getDeclaredFields()).forEach(System.out::println);
        //同理，只能获取public属性
        prettyPrint("c.getField(\"name\"");
        System.out.println(c.getField("name"));
        //可以获得所有属性
        prettyPrint("getDeclaredField(\"age\")");
        System.out.println(c.getDeclaredField("age"));

        //获得类的方法
        //获得该类及其父类的全部public方法
        prettyPrint("getMethods()");
        Arrays.stream(c.getMethods()).forEach(System.out::println);
        //获得该类的所有方法
        prettyPrint("getDeclaredMethods()");
        Arrays.stream(c.getDeclaredMethods()).forEach(System.out::println);
        //获得指定public方法
        prettyPrint("c.getMethod(\"setName\", String.class)");
        System.out.println(c.getMethod("setName", String.class));
        prettyPrint("c.getMethod(\"setName\", String.class)");
        System.out.println(c.getMethod("getName"));
        //获得指定方法（包括private方法）
        prettyPrint("c.getDeclaredMethod(\"myPrivateMethod\")");
        System.out.println(c.getDeclaredMethod("myPrivateMethod"));

        //获得类的构造器
        //获得该类所有public构造器
        prettyPrint("c.getConstructors()");
        System.out.println(Arrays.toString(c.getConstructors()));
        //获得该类所有构造器
        prettyPrint("c.getDeclaredConstructors()");
        System.out.println(Arrays.toString(c.getDeclaredConstructors()));
        //获得指定public构造器
        prettyPrint("c.getConstructor(String.class, int.class)");
        System.out.println(c.getConstructor(String.class, int.class));
        //获得指定构造器（包含私有构造器）
        System.out.println(c.getDeclaredConstructor(String.class));
    }

    /**
     * 打印总长度为100的分割线，并将方法名居中
     * @param methodName
     */
    public static void prettyPrint(String methodName){
        System.out.println();
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 100;
        int len = methodName.length();
        while(end - start > len){
            start++;
            end--;
            sb.append("=");
        }
        sb.append(methodName);
        for(int i = end ; i < 100 ; i++) {
            sb.append("=");
        }
        System.out.println(sb.toString());
        System.out.println();
    }
}
