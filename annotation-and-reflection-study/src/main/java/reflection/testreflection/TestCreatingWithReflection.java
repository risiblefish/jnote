package reflection.testreflection;

import testclass.MyClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过反射动态创建对象
 * @author Sean Yu
 */
public class TestCreatingWithReflection {
    public static void main(String[] args) throws Exception {
        //获得Class
        Class c = Class.forName("testclass.MyClass");
        //调用无参构造器动态创建对象
        /**
         * 注：
         * 根据JAVA11的API 我们可以看见反射中的newInstance（）方法不推荐使用了，用
         * clazz.getDeclaredConstructor().newInstance()代替
         */
        MyClass obj = (MyClass)c.newInstance();
        System.out.println(obj);
        MyClass obj1 = (MyClass) c.getDeclaredConstructor().newInstance();
        System.out.println(obj1);

        //通过构造器来创建对象
        Constructor publicConstructor = c.getDeclaredConstructor(String.class, int.class);
        Object instance1 = publicConstructor.newInstance("public", 1);
        System.out.println(instance1);
        //思考，如果通过反射获得private构造器，然后创建对象，能得到什么?
//        Constructor privateConstructor = c.getDeclaredConstructor(String.class);
//        privateConstructor.setAccessible(true);//magic
//        Object instance2 = privateConstructor.newInstance("sean");
//        System.out.println(instance2);

        //通过反射调用方法  Method.invoke(<调用方法的对象>,<方法的具体参数1>,<参数2>，...)
        MyClass obj3 = (MyClass) c.getDeclaredConstructor().newInstance();
        Method method = c.getDeclaredMethod("setName", String.class);
        method.invoke(obj3,"sean");
        System.out.println(obj3.getName());

        //通过反射操作属性 Field.set(<要操作属性的对象>,<新的值>)
        MyClass obj4 = (MyClass) c.getDeclaredConstructor().newInstance();
        //操作public属性
        Field name = c.getField("name");
        name.set(obj4,"newName");
        System.out.println(obj4.getName());
        //操作private属性，能否成功？
//        Field age = c.getDeclaredField("age");
//        age.set(obj4,10);
    }
}
