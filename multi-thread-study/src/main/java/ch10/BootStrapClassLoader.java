package ch10;

/**
 *
 * @Author: Sean Yu
 * @Date: 2021/2/26 7:21
 */
public class BootStrapClassLoader {
    public static void main(String[] args) {
        //根加载器是由c++编写的，负责JVM核心类库的加载，比如java.lang包，根加载器是最顶层的加载器，所以获取不到引用
        System.out.println(String.format("BootStrap:: %s", String.class.getClassLoader()));
        //可以通过如下系统属性来获得根加载器的加载路径
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
