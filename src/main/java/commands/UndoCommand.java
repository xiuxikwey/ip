package commands;

import java.io.IOException;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.NoResult;
import results.Result;

/**
 * Undos previous result.
 */
public class UndoCommand extends Command {
    private History history;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    /**
     * Saves objects for later use.
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.history = h;
        this.storage = s;
        this.taskList = t;
        this.ui = u;
    }

    /**
     * Undos previous result
     */
    public Result run() {
        try {
            history.undo();
            storage.updateStorage(taskList.getList());
            return new NoResult();
        } catch (IOException e) {
            ui.speak("Backup failed. Good luck!");
            return new NoResult();
        }
    }
}
