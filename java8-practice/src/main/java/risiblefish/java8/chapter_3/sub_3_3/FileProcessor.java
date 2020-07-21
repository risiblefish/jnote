package risiblefish.java8.chapter_3.sub_3_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;

/**
 * @program: java8
 * @description:
 * @author: Unuts
 * @create: 2020-03-13 22:22
 **/

public class FileProcessor {
    /**
     * lambda实践： 环绕执行(execute around)模式
     * <p>
     * 通过这个例子，来看看实践中如何利用lambda和行为参数来让代码更为灵活
     * <p>
     * 资源处理（如处理文件或数据库时）时常见的一个模式就是 打开一个资源 -> 做一些处理 -> 然后关闭资源
     * <p>
     * 设置和清理阶段总是很类似，并且会围绕着执行处理的那些重要代码。这就是所谓的环绕执行模式。
     * <p>
     * 比如下面这样：
     */
    //这里使用了java7中的带资源的try语句，它已经简化了代码，不需要再显式地关闭资源了
    public static String processFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/java/risiblefish/java8/chapter_3/sub_3_3/data.txt";
        try (
                BufferedReader br = new BufferedReader(
                        new FileReader(filePath))) {
            return br.readLine();
        }
    }


    /**
     * 上述代码只能返回文件的第1行，假如有很多需求，要求按需求来读取文件，思考一下该如何改进代码？
     * 我们可以把processFile行为参数化，更具体地说，让它变成一个接口，把行为传递给processFile,以便它利用BufferdReader执行不同的行为
     * 接下来思考如何实现：
     * 用函数式签名来看，processFile可以接受BufferedReader，也可以接收一个File，或是一个File的路径,并返回一个String
     * 同时这个接口要能够抛出异常
     */

    //创建一个接受BufferedReader的接口 , lambda签名： (BufferedReader br) -> String
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    public static String processFile(BufferedReaderProcessor p)
            throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/main/java/risiblefish/java8/chapter_3/sub_3_3/data.txt";
        try (
                BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                return p.process(br);
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println(processFile());

        //用lambda来读取文件所有内容
        System.out.println(processFile(
                (BufferedReader br) -> {
                    StringBuffer sb = new StringBuffer();
                    String currLine = null;
                    while((currLine = br.readLine())!= null){
                        sb.append(currLine + " ");
                    }
                    return sb.toString();
                }
        ));
    }
}
