package commands;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.NoResult;
import results.Result;

/**
 * Search for tasks given string.
 */
public class SearchCommand extends Command {

    private TaskList tasklist;
    private Ui ui;
    
    private String target;

    /**
     * Creates new command.
     * 
     * @param target String to search.
     */
    public SearchCommand(String target) {
        this.target = target;
    }

    /**
     * Saves objects for later use.
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.tasklist = t;
        this.ui = u;
    }

    /**
     * Searches for similar tasks.
     */
    public Result run() {
        ui.speak(tasklist.searchTask(target));
        return new NoResult();
    }

}
