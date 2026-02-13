package chatbot;

import java.util.ArrayList;

/**
 * Stores and returns messages from other classes.
 */
public class Ui {
   
    private ArrayList<String> responses;

    /**
     * Initialises list of responses.
     */
    public Ui() {
        this.responses = new ArrayList<>();
    }

    /**
     * Returns all responses as one string.
     * 
     * @return Response.
     */
    public String getResponse() {
        String result = String.join("\n", responses);
        responses.clear();
        return result;
    }

    /**
     * Adds message to list of responses.
     * 
     * @param input String to be added.
     */
    public void speak(String input) {
       responses.add(input);
    }
    
    /**
     * Capitalises and speaks str.
     * 
     * @param str String to be printed.
     */
    public void echo(String str) {
        if (str.length() > 0) {
            str = str.substring(0, 1).toUpperCase()
                    + str.substring(1);
        }
        speak(str + " to you too!");
    }
}
