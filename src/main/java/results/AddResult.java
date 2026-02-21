package results;

import chatbot.TaskList;
import chatbot.Ui;
import tasks.Task;

/**
 * Result of adding task at index.
 */
public class AddResult extends Result {

    private TaskList taskList;
    private Ui ui;

    int index;
    private Task task;
    
    /**
     * Initialises result.
     * 
     * @param index
     * @param task
     * @param taskList
     * @param ui
     */
    public AddResult(int index, Task task, TaskList taskList, Ui ui) {
        this.task = task;
        this.index = index;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Reverses this result and returns that result.
     */
    public Result reverse() {
        taskList.deleteAtIndex(index);
        ui.speak("Deleted " + task);
        return new DeleteResult(index, task, taskList, ui);
    }
}
