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
                } else if (str.startsWith("mark ")) {
                    str = str.substring(5);
                    updateTask(str, true);
                } else if (str.startsWith("unmark ")) {
                    str = str.substring(7);
                    updateTask(str, false);
                } else if (str.startsWith("todo ") 
                    || str.startsWith("deadline ")
                    || str.startsWith("event ")) {
                    storeTask(str);
                } else if (str.startsWith("delete ")) {
                    str = str.substring(7);
                    deleteTask(str);
                } else {
                    //capitalise and echo
                    if (str.length() > 0) {
                        str = str.substring(0, 1).toUpperCase()
                        + str.substring(1);
                    }
                    speak(str + " to you too!");
                }
            }
        }
    }

    private static void deleteTask(String trim) {
        try {
            Integer i = Integer.parseInt(trim);
            speak("Deleted: " + tasks.get(i));
            tasks.remove((int) i);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            speak("We do not have this task number.");
        }
    }

    private static void updateTask(String trim, boolean status) {
        try {
            Integer i = Integer.parseInt(trim);
            tasks.get(i).setDone(status);
            if (status) {
                speak("Consider it DONE!");
            } else {
                speak("The threads unravel.");
            }
            readTask();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            speak("We do not have this task number.");
        }
    }

    private static void storeTask(String str) {
        try {
            Task newTask = null;
            if (str.startsWith("todo ")) {
                str = str.substring(5);
                newTask = new ToDo(str);
            } else if (str.startsWith("deadline ")) {
                str = str.substring(9);
                String[] sarr = str.split(" /by ");
                if (sarr.length == 2) {
                    newTask = new Deadline(sarr[0], sarr[1]);
                } else {
                    speak("Try deadline A /by B.");
                    return;
                }
            } else {
                //starts with "event "
                str = str.substring(6);
                String[] nameArr = str.split(" /from ");
                if (nameArr.length == 2) {
                    String[] timeArr = nameArr[1].split(" /to ");
                    if (timeArr.length == 2) {
                        newTask = new Event(nameArr[0], timeArr[0], timeArr[1]);
                    } else {
                        speak("Try event A /from B /to C.");
                        return;
                    }
                } else {
                    speak("Try event A /from B /to C.");
                    return;
                }
            }
            tasks.add(newTask);
            speak("Next task is to \"" + newTask + "\"!");
        } catch (EmptyStringException e) {
            speak(e.getMessage());
        }
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
        *****************************""";
        System.out.println(drawing);
        speak(
        """
        Oliver, King Of The Night, at your service!
        I know "todo", "deadline", "event", "list", "mark", "unmark", "delete" and "bye"!
        What shall we do next?""");
    }

    private static void sayGoodbye() {
        String drawing = """
              A    A
            <  U w U >
              /    \\
          o__/      \\  
        *****************************""";
        System.out.println(drawing);
        speak("Goodbye!");
    }

    public static void main(String[] args) {
        greet();
        event_handler();
    }
}
