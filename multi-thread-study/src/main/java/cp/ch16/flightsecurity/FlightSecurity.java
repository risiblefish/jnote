package cp.ch16.flightsecurity;

/**
 * 非线程安全的设计
 * @author Sean Yu
 */
public class FlightSecurity {
    private int count = 0;

    private String boardingPass = "null";

    private String idCard = "null";

    public void pass(String boardingPass, String idCard) {
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if (boardingPass.charAt(0) != idCard.charAt(0)) {
            throw new RuntimeException(String.format("Exception [%s]", toString()));
        }
    }

    @Override
    public String toString() {
        return String.format("The %s passengers, boardingPass[%s] idCard[%s]", count, boardingPass, idCard);
    }
}
