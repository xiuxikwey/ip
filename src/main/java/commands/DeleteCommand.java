package commands;

import java.io.IOException;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.DeleteResult;
import results.NoResult;
import results.Result;
import tasks.Task;

/**
 * Updates isDone of the task at index.
 */
public class DeleteCommand extends Command {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;
    
    private String input;
    private Task task;
    
    /**
     * Initialises parameters.
     * 
     * @param index Index of task in taskList.
     * @param isDone Desired isDone of task.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Saves classes for later use.
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.storage = s;
        this.tasklist = t;
        this.ui = u;
    }

    /**
     * Deleted task at input index.
     */
    public Result run() {
        try {
            task = tasklist.deleteIndex(input);
            ui.speak("Deleted " + task);
            storage.updateStorage(tasklist.getList());
            return new DeleteResult(task, tasklist, ui);
        } catch (IOException e) {
            ui.speak("Save to file failed.");
            return new DeleteResult(task, tasklist, ui);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.speak("We do not have this task number.");
            return new NoResult();
        }
    }
}
