package chatbot;

import java.util.ArrayList;

/**
 * Used by other classes to communicate with user.
 */
public class Ui {
   
    private static ArrayList<String> responses = new ArrayList<>();

    /**
     * Returns last response from Ui and clears storage.
     * 
     * @return Last response.
     */
    public static ArrayList<String> getResponses() {
        ArrayList<String> temp = Ui.responses;
        Ui.responses = new ArrayList<>();
        return temp;
    }

    /**
     * Prints input in red colour.
     * Updates guiResponse.
     * 
     * @param input String to be printed.
     */
    public static void speak(String input) {
       Ui.responses.add(input);
    }
    
    /**
     * Capitalises and speaks str.
     * 
     * @param str String to be printed.
     */
    public static void echo(String str) {
        //unrecognised, capitalise and echo
        if (str.length() > 0) {
            str = str.substring(0, 1).toUpperCase()
                    + str.substring(1);
        }
        speak(str + " to you too!");
    }
    
    /**
     * Prints goodbye message.
     */
    public static void sayGoodbye() {
        speak("Goodbye!");
    }
}
