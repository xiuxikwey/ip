package commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import chatbot.History;
import chatbot.Parser;
import chatbot.ParserException;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.NoResult;
import results.Result;

/**
 * Updates isDone of the task at index.
 */
public class LoadFromStorageCommand extends Command {

    private Parser parser;
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Saves classes for later use.
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.parser = p;
        this.storage = s;
        this.tasklist = t;
        this.ui = u;
    }

    /**
     * Loads tasks from storage
     */
    public Result run() {
        try {
            tasklist.getTasksFrom(storage.getList(parser));
            return new NoResult();
        } catch (FileNotFoundException e) {
            ui.speak("Starting a new list...");
            return new NoResult();
        } catch (IOException e) {
            ui.speak("Save to file failed.");
            return new NoResult();
        } catch (ParserException e) {
            ui.speak(e.getMessage());
            return new NoResult();
        }
    }
}
