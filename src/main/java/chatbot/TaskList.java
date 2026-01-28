package chatbot;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * List of tasks
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Removes task from list
     * 
     * @param trim task number in string format
     */
    public static void deleteTask(String trim) {
        try {
            Integer i = Integer.parseInt(trim);
            Ui.speak("Deleted: " + tasks.get(i));
            tasks.remove((int) i);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.speak("We do not have this task number.");
        }
    }

    /**
     * Set task isDone for a task number
     * 
     * @param trim task number as string
     * @param status future state of task
     */
    public static void updateTask(String trim, boolean status) {
        try {
            Integer i = Integer.parseInt(trim);
            tasks.get(i).setDone(status);
            if (status) {
                Ui.speak("Consider it DONE!");
            } else {
                Ui.speak("The threads unravel.");
            }
            readTasks();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.speak("We do not have this task number.");
        }
    }

    /**
     * Create new task
     * 
     * @param str unparsed user input
     */
    public static void storeTask(String str) {
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
                    Ui.speak("Try deadline A /by B.");
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
                        Ui.speak("Try event A /from B /to C.");
                        return;
                    }
                } else {
                    Ui.speak("Try event A /from B /to C.");
                    return;
                }
            }
            tasks.add(newTask);
            Ui.speak("Next task is to \"" + newTask + "\"!");
        } catch (EmptyStringException e) {
            Ui.speak(e.getMessage());
        }
    }

    /**
     * Print out all tasks
     */
    public static void readTasks() {
        ListIterator<Task> iter = tasks.listIterator();
        Ui.speak("""
        ###################
        ## SEIZE THE DAY""");
        while (iter.hasNext()) {
            Ui.speak("## " + iter.nextIndex() + ": " + iter.next());
        }
    }
}
