package ch10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: Sean Yu
 * @Date: 2021/2/26 7:40
 */
//自定义类加载器必须是ClassLoader的子类或间接子类
public class MyClassLoader extends ClassLoader {
    //定义默认的class存放路径  E:\classloader1
    private final static Path DEFAULT_CLASS_DIR = Paths.get("E", "classloader1");

    private final Path classDir;

    //构造器1 ： 使用默认的class路径
    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    //构造器2 ： 允许传入指定的class路径
    public MyClassLoader(String classDir) {
        super();
        this.classDir = Paths.get(classDir);
    }

    //构造器3 ： 指定class路径的同时，指定父 类加载器
    public MyClassLoader(String classDir, ClassLoader parent) {
        super(parent);
        this.classDir = Paths.get(classDir);
    }

    //重写父类的findClass方法，这是至关重要的步骤
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //读取class的二进制数据
        byte[] classBytes = this.readClassBytes(name);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException(String.format("Cannot load the class [%s]", name));
        }
        //调用defineClass方法定义class
        //需要强调是该方法第第3,4个入参，即读取的起始位置和长度，之所以要传入，是因为读取到的字节数组，可能包含了多个class的信息，即可能不只一个class,所以要读取其中某个class的完整信息，需要指定这2个参数
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    //将class文件读入内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        //将包名分隔符转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if (!classFullPath.toFile().exists()) {
            throw new ClassNotFoundException(String.format("The class[%s] not found.", name));
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException(String.format("The class[%s] occur error", e));
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
