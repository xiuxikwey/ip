package chatbot;

/**
 * Chatbot called Oliver.
 */
public class Oliver {
    public static void main(String[] args) {
        TaskList.recover();
        Ui.greet();
        Parser.takeInput();
    }
}
