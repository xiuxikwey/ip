package commands;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.NoResult;
import results.Result;

public class ListCommand extends Command {

    private TaskList tasklist;
    private Ui ui;
    
    /**
     * Saves classes for later use.
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.tasklist = t;
        this.ui = u;
    }

    /**
     * Prints out task list.
     */
    public Result run() {
        ui.speak(tasklist.readTasks());
        return new NoResult();
    }
}
