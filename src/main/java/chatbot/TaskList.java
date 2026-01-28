package chatbot;

import java.util.ArrayList;
import java.util.ListIterator;

import tasks.Task;

/**
 * List of tasks
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Loads taskList from Storage
     */
    public static void recover() {
        tasks = Storage.getList();
    }

    /**
     * Removes task from list
     * 
     * @param trim task number in string format
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
     * Set task isDone for a task number
     * 
     * @param trim task number as string
     * @param status future state of task
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
     * Add task to list
     * 
     * @param Task or null
     */
    public static void storeTask(Task newTask) {
        if (newTask == null) {
            return;
        }
        tasks.add(newTask);
        Storage.updateStorage(tasks);
        Ui.speak("Next task is to \"" + newTask + "\"!");
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
