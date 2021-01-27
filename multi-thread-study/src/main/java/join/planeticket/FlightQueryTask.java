package join.planeticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Sean Yu
 */
public class FlightQueryTask extends Thread implements FlightQuery {

    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FlightQueryTask(String airline, String origin,String destination){
        super(String.format("[%s]",airline));
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }

    @Override
    public void run(){
        System.out.println(String.format("%s-query from %s to %s",getName(),origin,destination));
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName() + "-" + randomVal);
            System.out.println(String.format("The flight : %s list query successful",getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
