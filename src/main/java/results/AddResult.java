package results;

import chatbot.TaskList;
import chatbot.Ui;
import tasks.Task;

public class AddResult extends Result {
    private Task task;
    private TaskList taskList;
    private Ui ui;

    public AddResult(Task task, TaskList taskList, Ui ui) {
        this.task = task;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Reverses this result and returns own reverse.
     */
    public Result reverse() {
        taskList.deleteTask(task);
        ui.speak("Deleted " + task);
        return new DeleteResult(task, taskList, ui);
    }
}
