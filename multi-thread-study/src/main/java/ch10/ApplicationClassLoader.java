package ch10;

/**
 * @Author: Sean Yu
 * @Date: 2021/2/26 7:39
 */
public class ApplicationClassLoader {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
}
