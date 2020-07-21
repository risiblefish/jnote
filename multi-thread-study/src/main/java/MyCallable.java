import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @program: multi-thread-study
 * @description:
 * @author: Unuts
 * @create: 2020-07-08 21:37
 **/

public class MyCallable implements Callable<Boolean> {

    private String url;
    private String outputFileName;

    public MyCallable(String url, String outputFileName) {
        this.url = url;
        this.outputFileName = outputFileName;
    }

    @Override
    public Boolean call() {
        try {
            download(url,outputFileName);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void download(String url, String outputFileName) throws IOException {
        FileUtils.copyURLToFile(new URL(url), new File(outputFileName));
        System.out.println(String.format("%s download succeed",outputFileName));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String url1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594141109610&di=9794a863cdcb3d9fdafd5ee3863e330e&imgtype=0&src=http%3A%2F%2Fhbimg.huabanimg.com%2F40dd8516266ee37b52548c88d9582333235509d29852-kk5Slg_fw658";
        String url2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594141240993&di=1b43394d5e67ab5f7c560b7accb4efa2&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D1929291001%2C286523461%26fm%3D214%26gp%3D0.jpg";
        String url3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594141109605&di=db7cd1256ed744bf5f6ffa176dabebd7&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3D73c1c44b2c738bd4c421b239918a876c%2Fed84473d269759ee9418db5db0fb43166c22df5f.jpg";

        MyCallable task1 = new MyCallable(url1, "corgi1.jpg");
        MyCallable task2 = new MyCallable(url2, "corgi2.jpg");
        MyCallable task3 = new MyCallable(url3, "corgi3.jpg");
        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Boolean> res1 = service.submit(task1);
        Future<Boolean> res2 = service.submit(task2);
        Future<Boolean> res3 = service.submit(task3);

        System.out.println(res1.get());
        System.out.println(res2.get());
        System.out.println(res3.get());

        service.shutdown();
    }
}
