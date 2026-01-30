package chatbot;

/**
 * Chatbot called Oliver.
 */
public class Oliver {
    /**
     * Starts chatbot.
     * 
     * @param args Ignored.
     */
    public static void main(String[] args) {
        TaskList.recover();
        Ui.greet();
        Parser.takeInput();
    }
}
