package chatbot;

/**
 * Prints to System.out to communicate with user.
 */
public class Ui {
   
    /**
     * Prints input in red colour.
     * 
     * @param input String to be printed.
     */
    public static void speak(String input) {
        System.out.println("\u001B[31m"
                + input
                + "\u001B[0m");
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
     * Prints welcome message.
     */
    public static void greet() {
        String drawing = """
         ___               ___
        (___\\  /~\\   /~\\  /___)
          (__\\ < O w O > /__)
              (   w  w  )
          o__/  mm   mm
        *****************************""";
        System.out.println(drawing);
        speak(
        """
        Oliver, King Of The Night, at your service!
        I know "todo", "deadline", "event", "list", "mark", "unmark", "delete" and "bye"!
        What shall we do next?""");
    }

    /**
     * Prints goodbye message.
     */
    public static void sayGoodbye() {
        String drawing = """
              A    A
            <  U w U >
              /    \\
          o__/      \\  
        *****************************""";
        System.out.println(drawing);
        speak("Goodbye!");
    }
}
