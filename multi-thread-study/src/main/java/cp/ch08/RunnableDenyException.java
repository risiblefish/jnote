package cp.ch08;

/**
 * @author Sean Yu
 */
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message){
        super(message);
    }
}
