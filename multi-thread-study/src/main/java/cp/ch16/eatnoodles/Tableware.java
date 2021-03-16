package cp.ch16.eatnoodles;

/**
 * @author Sean Yu
 */
public class Tableware {
    private final String toolName;

    public Tableware(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return String.format("Tool : [%s]", toolName);
    }
}
