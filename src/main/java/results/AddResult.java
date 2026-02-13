package results;

import chatbot.TaskList;
import chatbot.Ui;
import tasks.Task;

public class AddResult extends Result {

    private TaskList taskList;
    private Ui ui;

    int index;
    private Task task;
    
    public AddResult(int index, Task task, TaskList taskList, Ui ui) {
        this.task = task;
        this.index = index;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Reverses this result and returns own reverse.
     */
    public Result reverse() {
        taskList.deleteAtIndex(index);
        ui.speak("Deleted " + task);
        return new DeleteResult(index, task, taskList, ui);
    }
}
