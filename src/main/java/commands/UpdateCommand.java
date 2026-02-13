package commands;

import java.io.IOException;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.NoResult;
import results.Result;
import results.UpdateResult;

/**
 * Updates isDone of the task at index.
 */
public class UpdateCommand extends Command {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    private String index;
    private boolean nextIsDone;
    
    /**
     * Initialises parameters.
     * 
     * @param index Index of task in taskList.
     * @param isDone Desired isDone of task.
     */
    public UpdateCommand(String index, boolean nextIsDone) {
        this.index = index;
        this.nextIsDone = nextIsDone;
    }

    /**
     * Saves classes for later use.
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.storage = s;
        this.taskList = t;
        this.ui = u;
    }

    /**
     * Updates state of tasks.
     */
    public Result run() {
        try {
            boolean previousIsDone = taskList.markAtIndex(index, this.nextIsDone);
            if (this.nextIsDone) {
                ui.speak("Consider it DONE!");
            } else {
                ui.speak("The threads unravel.");
            }
            ui.speak(taskList.readTasks());
            this.nextIsDone = previousIsDone;
            storage.updateStorage(taskList.getList());
            return new UpdateResult(this);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.speak("We do not have this task number.");
            return new NoResult();
        } catch (IOException e) {
            ui.speak("Save to file failed.");
            return new UpdateResult(this);
        }
    }
}
