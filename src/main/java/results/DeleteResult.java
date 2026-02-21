package results;

import chatbot.TaskList;
import chatbot.Ui;
import tasks.Task;

/**
 * Result of deleting task at index.
 */
public class DeleteResult extends Result {
    private int index;
    private Task task;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises result.
     * 
     * @param index
     * @param task
     * @param taskList
     * @param ui
     */
    public DeleteResult(int index, Task task, TaskList taskList, Ui ui) {
        this.index = index;
        this.task = task;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Reverses this result and returns that result.
     */
    public Result reverse() {
        taskList.storeTaskAtIndex(task, index);
        ui.speak("Added " + task);
        return new AddResult(index, task, taskList, ui);
    }
}
