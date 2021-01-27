package join.planeticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sean Yu
 */
public class FlightQueryExample {
    private static List<String> flightCompanies = Arrays.asList("china-east", "spring", "china-south");

    public static void main(String[] args) {
        List<String> results = search("chongqing", "shanghai");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original, String dest) {
        final List<String> result = new ArrayList<>();

        List<FlightQueryTask> tasks = flightCompanies.stream().map(flightCompanyName -> createSearchTask(flightCompanyName, original, dest)).collect(Collectors.toList());
        tasks.forEach(Thread::start);
        //分别调用每个子线程的join方法，来阻塞当前线程（即主线程）
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //在每个子线程执行完毕之前，这句话不会被执行
        tasks.stream().map(FlightQueryTask::get).forEach(result::addAll);
        return result;
    }

    private static FlightQueryTask createSearchTask(String flight, String origin, String dest) {
        return new FlightQueryTask(flight, origin, dest);
    }
}
