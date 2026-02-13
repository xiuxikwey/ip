package chatbot;

import java.util.ArrayList;

import tasks.Task;

/**
 * Manages operations on list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initialises taskList from list.
     */
    public void getTasksFrom(ArrayList<Task> list) {
        this.tasks = list;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Set task.isDone at an index.
     * Returns original isDone so undo is possible.
     * 
     * @param index Index as a string.
     * @param isDone Desired isDone.
     * @return Original isDone of task.
     * @throws NumberFormatException
     * @throws IndexOutOfBoundsException
     */
    public boolean markAtIndex(String index, boolean isDone)
            throws NumberFormatException, IndexOutOfBoundsException {
        Integer i = Integer.parseInt(index);
        Task t = tasks.get(i);
        boolean previousIsDone = t.getDone();
        t.setDone(isDone);
        return previousIsDone;
    }

    /**
     * Add new task to list.
     * 
     * @param Task
     */
    public void storeTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes task from list.
     * 
     * @param target
     */
    public void deleteTask(Task target) {
        for (int i = tasks.size() - 1; i >= 0; i--) {
            if (tasks.get(i).equals(target)) {
                tasks.remove(i);
            }
        }
    }

    /**
     * Removes task at index.
     * 
     * @param trim Index as string.
     * @return Task that was deleted.
     * @throws NumberFormatException
     * @throws IndexOutOfBoundsException
     */
    public Task deleteIndex(String trim) throws
            NumberFormatException, IndexOutOfBoundsException {
        Integer i = Integer.parseInt(trim);
        Task deleted = this.tasks.get(i);
        this.tasks.remove((int) i);
        return deleted;
    }

    /**
     * Returns list of tasks as a string.
     * 
     * @return String
     */
    public String readTasks() {
        String result = """
        ###################
        ## SEIZE THE DAY""";
        for (int i = 0; i < tasks.size(); i++) {
            result = result.concat("\n" + "## " + String.valueOf(i)
                    + ": " + tasks.get(i)); 
        }
        return result;
    }

    /**
     * Returns tasks similar to search term.
     * 
     * @param str Names of tasks.
     */
    public String searchTask(String str) {
        String result = """
        None shall escape""";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.toString().contains(str)) {
                result = result.concat("\n## " + i + ": " + t.toString());
            }
        }
        return result;
    }
}
