package commands;

import java.io.IOException;

import chatbot.History;
import chatbot.Parser;
import chatbot.ParserException;
import chatbot.Storage;
import chatbot.TaskList;
import chatbot.Ui;
import results.AddResult;
import results.NoResult;
import results.Result;
import tasks.Task;

/**
 * Adds new task.
 */
public class AddCommand extends Command {

    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    
    private int index;
    private String input;
    private Task task;
    
    /**
     * Initialises parameters.
     * 
     * @param input User input.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Saves classes for later use.
     */
    public void bind(History h, Parser p, Storage s, TaskList t, Ui u) {
        this.parser = p;
        this.storage = s;
        this.taskList = t;
        this.ui = u;
    }

    /**
     * Adds new task.
     */
    public Result run() {
        try {
            task = parser.userInputToTask(input);
            index = taskList.storeTask(task);
            ui.speak("Next task is to \"" + task + "\"!");
            storage.updateStorage(taskList.getList());
            return new AddResult(index, task, taskList, ui);
        } catch (IOException e) {
            ui.speak("Save to file failed.");
            return new AddResult(index, task, taskList, ui);
        } catch (ParserException e) {
            ui.speak(e.getMessage());
            return new NoResult();
        }
    }
}
