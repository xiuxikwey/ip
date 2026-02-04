package chatbot;

/**
 * Thrown when parser cannot read input.
 */
public class ParserException extends Exception {

    /**
     * Creates new exception.
     * @param m Message.
     */
    public ParserException(String m) {
        super(m);
    }
    
}
