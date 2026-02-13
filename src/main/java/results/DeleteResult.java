package results;

import chatbot.TaskList;
import chatbot.Ui;
import tasks.Task;

public class DeleteResult extends Result {
    private Task task;
    private TaskList taskList;
    private Ui ui;

    public DeleteResult(Task task, TaskList taskList, Ui ui) {
        this.task = task;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Reverses this result and returns own reverse.
     */
    public Result reverse() {
        taskList.storeTask(task);
        ui.speak("Added " + task);
        return new AddResult(task, taskList, ui);
    }
}
