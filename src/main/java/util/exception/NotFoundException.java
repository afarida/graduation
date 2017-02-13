package util.exception;

/**
 * Created by Admin on 13.02.2017.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
