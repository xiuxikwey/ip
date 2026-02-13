package commands;

import java.io.IOException;

import chatbot.History;
import chatbot.Parser;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.NoResult;
import results.Result;

public class UndoCommand extends Command {
    private History history;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.history = h;
        this.storage = s;
        this.taskList = t;
        this.ui = u;
    }

    /**
     * Closes program immediately.
     */
    public Result run() {
        try {
            history.undo();
            storage.updateStorage(taskList.getList());
            return new NoResult();
        } catch (IOException e) {
            ui.speak("Save to file failed.");
            return new NoResult();
        }
    }
}
