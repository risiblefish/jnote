package cp.ch16.flightsecurity;

/**
 * @author Sean Yu
 */
public class FlightSecurityTest {
    static class Passergers extends Thread {
        private final FlightSecurity flightSecurity;
        private final String idCard;
        private final String boardingPass;

        public Passergers(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true) {
                //
                flightSecurity.pass(boardingPass, idCard);
            }
        }
    }

    /**
     * 测试发现： 仍然会有抛出异常的情况，请思考是什么原因
     *
     * 答：FlightSecurity类的pass方法，需要先进行赋值，然后进行check()，赋值不是线程安全的，因为存在cpu的时间片切换
     * 解决办法： 在pass方法前使用synchronized关键字
     * @param args
     */
    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passergers(flightSecurity, "A123", "AF123").start();
        new Passergers(flightSecurity, "B123", "BF123").start();
        new Passergers(flightSecurity, "C123", "CF123").start();
    }

}
