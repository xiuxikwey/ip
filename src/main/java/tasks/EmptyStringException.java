package tasks;

/**
 * Exception thrown when task fields are empty string.
 */
public class EmptyStringException extends Exception {
    public EmptyStringException(String m) {
        super(m);
    }
}