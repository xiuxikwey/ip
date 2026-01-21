import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Oliver {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    private static void event_handler() {
        try(Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("bye")) {
                    sayGoodbye();
                } else if (str.equalsIgnoreCase("list")) {
                    readTask();
                } else if (str.matches("^mark \\d+$")) {
                    String trim = str.substring(5);
                    updateTask(trim, true);
                } else if (str.matches("^unmark \\d+$")) {
                    String trim = str.substring(7);
                    updateTask(trim, false);
                } else {
                    storeTask(str);
                }
            }
        }
    }

    private static void updateTask(String trim, boolean status) {
        try {
            Integer i = Integer.parseInt(trim);
            tasks.get(i).setDone(status);
            if (status) {
                speak("Consider it DONE!");
            } else {
                speak("The toils of man know no end");
            }
            readTask();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            speak("We do not have this task number.");
        }
    }

    private static void storeTask(String input) {
        tasks.add(new Task(input));
        speak("Next we will \"" + input + "\"!");
    }

    private static void readTask() {
        ListIterator<Task> iter = tasks.listIterator();
        speak("""
        ###################
        ## SEIZE THE DAY""");
        while (iter.hasNext()) {
            speak("## " + iter.nextIndex() + ": " + iter.next());
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
        I know \"list\", \"mark <number>\", \"unmark <number>\"and \"bye\"!
        What shall we do next?

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
