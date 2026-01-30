package chatbot;

import java.util.ArrayList;
import java.util.ListIterator;

import tasks.Task;

/**
 * List of tasks with add, delete, list functionality.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initialises taskList from Storage.
     */
    public static void recover() {
        tasks = Storage.getList();
    }

    /**
     * Removes task from list.
     * 
     * @param trim Task number as a string.
     */
    public static void deleteIndex(String trim) {
        try {
            Integer i = Integer.parseInt(trim);
            Ui.speak("Deleted: " + tasks.get(i));
            tasks.remove((int) i);
            Storage.updateStorage(tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.speak("We do not have this task number.");
        }
    }

    /**
     * Set task isDone for a task number.
     * 
     * @param trim Task number as string.
     * @param status Desired isDone of task.
     */
    public static void updateIndex(String trim, boolean status) {
        try {
            Integer i = Integer.parseInt(trim);
            tasks.get(i).setDone(status);
            if (status) {
                Ui.speak("Consider it DONE!");
            } else {
                Ui.speak("The threads unravel.");
            }
            Storage.updateStorage(tasks);
            readTasks();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.speak("We do not have this task number.");
        }
    }

    /**
     * Add new task to list.
     * 
     * @param Task
     */
    public static void storeTask(Task newTask) {
        tasks.add(newTask);
        Storage.updateStorage(tasks);
        Ui.speak("Next task is to \"" + newTask + "\"!");
    }

    /**
     * Print out all tasks.
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
