import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Oliver {
    private static ArrayList<String> strArr = new ArrayList<String>();

    private static void event_handler() {
        try(Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("bye")) {
                    sayGoodbye();
                } else if (str.equalsIgnoreCase("list")) {
                    readItems();
                } else {
                    storeItem(str);
                }
            }
        }
    }

    private static void storeItem(String input) {
        strArr.add(input);
        speak("Stored \"" + input + "\".");
    }

    private static void readItems() {
        ListIterator<String> iter = strArr.listIterator();
        speak("Reading from my records:");
        while (iter.hasNext()) {
            speak(iter.nextIndex() + ": " + iter.next());
        }
    }

    private static void speak(String input) {
        System.out.println("\u001B[31m"
            + input
            + "\u001B[0m");
    }

    private static void greet() {
        String drawing = """
         ___               ___
        (___\\  /~\\   /~\\  /___)
          (__\\ < O w O > /__)
              (   w  w  )
          o__/  mm   mm
        *****************************
        """;
        System.out.println(drawing);
        speak(
        """
        Oliver, King Of The Night, at your service!
        I keep track of things you say, which you can recall by saying \"list\".
        You can exit by saying \"bye\"!
        """);
    }

    private static void sayGoodbye() {
        String drawing = """
              A    A
            <  U w U >
              /    \\
          o__/      \\  
        *****************************
        """;
        System.out.println(drawing);
        speak("Goodbye!");
    }

    public static void main(String[] args) {
        greet();
        event_handler();
    }
}
