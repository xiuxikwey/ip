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
     * Returns original state so undo is possible.
     * 
     * @param index Index as a string.
     * @param isDone Desired isDone.
     * @return Original isDone.
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
     * @return Index of task.
     */
    public int storeTask(Task newTask) {
        assert(newTask != null);
        tasks.add(newTask);
        return tasks.size() - 1;
    }

    /**
     * Insert task at index.
     * 
     * @param Task
     */
    public void storeTaskAtIndex(Task newTask, int index) {
        assert(newTask != null);
        tasks.add(index, newTask);
    }

    /**
     * Removes task at index.
     * 
     * @param index
     * @return Deleted task.
     */
    public Task deleteAtIndex(int index) {
        Task target = tasks.get(index);
        tasks.remove(index);
        return target;
    }

    /**
     * Removes task at index.
     * 
     * @param trim Index as string.
     * @return Task that was deleted.
     * @throws NumberFormatException
     * @throws IndexOutOfBoundsException
     */
    public Task deleteAtIndex(String trim) throws
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
