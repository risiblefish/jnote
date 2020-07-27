package testperformance;

import org.springframework.util.StopWatch;
import testclass.MyClass;

import java.lang.reflect.Method;

/**
 * 分析反射的性能
 *
 * @author Sean Yu
 */
public class TestReflectionPerformance{
    //总的测试次数
    static final int TEST_TIMES = 100_000_000;

    //通过常规方式调用
    static long test01() {
        MyClass obj1 = new MyClass();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < TEST_TIMES; i++) {
            obj1.getName();
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return stopWatch.getTotalTimeNanos();
    }

    //通过反射调用
    static long test02() throws Exception {
        MyClass obj2 = new MyClass();
        Class c = obj2.getClass();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Method method = c.getMethod("getName");
        for (int i = 0; i < TEST_TIMES; i++) {
            method.invoke(obj2);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return stopWatch.getTotalTimeNanos();
    }

    //通过反射但关闭安全监测调用
    static long test03() throws Exception {
        MyClass obj3 = new MyClass();
        Class c = obj3.getClass();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Method method = c.getMethod("getName");
        method.setAccessible(true);
        for (int i = 0; i < TEST_TIMES; i++) {
            method.invoke(obj3);
        }
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return stopWatch.getTotalTimeNanos();
    }

    public static void main(String[] args) throws Exception {
        long t1 = test01();
        long t2 = test02();
        long t3 = test03();
        System.out.println(String.format("t1 : t2 : t3 = %s : %s : %s", 1 , t2/t1, t3/t1));
    }
}
