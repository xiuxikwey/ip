package chatbot;

import java.util.ArrayList;

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
        assert(tasks != null);
    }

    /**
     * Removes task from list.
     * 
     * @param trimmed Task number as a string.
     */
    public static void deleteIndex(String trimmed) {
        try {
            Integer i = Integer.parseInt(trimmed);
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
     * @param trimmed Task number as string.
     * @param status Desired isDone of task.
     */
    public static void markAtIndex(String trimmed, boolean status) {
        try {
            Integer i = Integer.parseInt(trimmed);
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
        assert(newTask != null);
        tasks.add(newTask);
        Storage.updateStorage(tasks);
        Ui.speak("Next task is to \"" + newTask + "\"!");
    }

    /**
     * Print out all tasks.
     */
    public static void readTasks() {
        Ui.speak("""
        ###################
        ## SEIZE THE DAY""");
        for (int i = 0; i < tasks.size(); i++) {
            Ui.speak("## " + i + ": " + tasks.get(i));
        }
    }

    /**
     * Prints tasks similar to search term.
     * 
     * @param str
     */
    public static void searchTask(String str) {
        Ui.speak("None shall escape");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().contains(str)) {
                Ui.speak("## " + i + ": " + t.toString());
            }
        }
    }
}
